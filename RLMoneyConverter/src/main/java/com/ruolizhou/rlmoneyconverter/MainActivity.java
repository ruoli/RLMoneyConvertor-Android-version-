package com.ruolizhou.rlmoneyconverter;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.ruolizhou.convertermodel.ConvertData;
import com.ruolizhou.convertermodel.ConverterUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends Activity implements View.OnClickListener{

    private ImageButton fromCountryCurrencyFlag, toCountryCurrencyFlag;
    private Button convertGOButton;
    private EditText fromCurrencyAmount, toCurrencyAmount;
    private GoogleCurrencyConverter googleCurrencyConverter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        fromCountryCurrencyFlag = (ImageButton)findViewById(R.id.fromCountryCurrencyFlag);
        fromCountryCurrencyFlag.setOnClickListener(this);
        toCountryCurrencyFlag = (ImageButton)findViewById(R.id.toCountryCurrencyFlag);
        toCountryCurrencyFlag.setOnClickListener(this);
        convertGOButton = (Button)findViewById(R.id.convertGOButton);
        convertGOButton.setOnClickListener(this);

        fromCurrencyAmount = (EditText)findViewById(R.id.fromCurrencyAmount);
        toCurrencyAmount = (EditText)findViewById(R.id.toCurrencyAmount);

        googleCurrencyConverter = new GoogleCurrencyConverter();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.convertGOButton:
                int fromAmount = Integer.parseInt(fromCurrencyAmount.getText().toString());
                ConvertData convertData = new ConvertData();
                convertData.setFromAmount(fromAmount);
                convertData.setFromCurrency("GBP");
                convertData.setToCurrency("USD");

                GoogleCurrencyConverter googleCurrencyConverter = new GoogleCurrencyConverter();
                googleCurrencyConverter.execute(convertData);

                break;
        }
    }



    private class GoogleCurrencyConverter extends AsyncTask<ConvertData, Void, String> {

        public GoogleCurrencyConverter() {
        }


        @Override
        protected String doInBackground(ConvertData... convertDatas) {
            HttpClient client = new DefaultHttpClient();
            HttpUriRequest request = new HttpGet("http://www.google.com/ig/calculator?hl=en&q=" + convertDatas[0].getFromAmount() + convertDatas[0].getFromCurrency() + "=?" + convertDatas[0].getToCurrency());
            HttpResponse response = null;
            try {
                response = client.execute(request);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String currencyJsonString = null;
            if (response != null) {
                try {
                    currencyJsonString = ConverterUtils.convertToString(response.getEntity().getContent());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            JSONObject currencyJsonObject = null;
            try {
                currencyJsonObject = new JSONObject(currencyJsonString);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String result = null;
            if (currencyJsonObject != null) {
                try {
                    result = currencyJsonObject.getString("rhs");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return result;
        }


        @Override
        protected void onPostExecute(String result) {
            toCurrencyAmount.setText(result);
        }
    }
}

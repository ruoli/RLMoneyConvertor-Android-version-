package com.ruolizhou.rlmoneyconverter;

import android.app.ListActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CurrencyChoosenActivity extends ListActivity {

    String[] currencyType = new String[] {"USD", "GBP", "EUR", "CNY", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_choosen);



        setListAdapter(new ArrayAdapter<String>(CurrencyChoosenActivity.this, android.R.layout.simple_list_item_1, currencyType));

    }

    @Override
    protected void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        String currencyTypeListPosition = currencyType[position];
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.currency_choosen, menu);
        return true;
    }
    
}

package com.ruolizhou.convertermodel;

/**
 * Created by apple on 07/09/2013.
 */
public class ConvertData {
    String fromCurrency;
    String toCurrency;
    int fromAmount;

    public String getFromCurrency() {
        return fromCurrency;
    }

    public void setFromCurrency(String fromCurrency) {
        this.fromCurrency = fromCurrency;
    }

    public String getToCurrency() {
        return toCurrency;
    }

    public void setToCurrency(String toCurrency) {
        this.toCurrency = toCurrency;
    }

    public int getFromAmount() {
        return fromAmount;
    }

    public void setFromAmount(int fromAmount) {
        this.fromAmount = fromAmount;
    }
}

package com.example.android.currencyconverttracking.Model;

import com.google.gson.annotations.SerializedName;

public class Quotes {

    @SerializedName("USD")
    private Currency uSD;

    public Currency getUSD() {
        return uSD;
    }

    public void setUSD(Currency uSD) {
        this.uSD = uSD;
    }
}

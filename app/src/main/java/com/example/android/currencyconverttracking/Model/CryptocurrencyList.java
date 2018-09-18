package com.example.android.currencyconverttracking.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CryptocurrencyList {

    @SerializedName("data")
    private ArrayList<Cryptocurrency> cryptocurrencyList;

    public ArrayList<Cryptocurrency> getCryptocurrencyArrayList() {
        return cryptocurrencyList;
    }

    public void setCryptocurrencyList(ArrayList<Cryptocurrency> cryptocurrencyArrayList) {
        this.cryptocurrencyList = cryptocurrencyArrayList;
    }
}

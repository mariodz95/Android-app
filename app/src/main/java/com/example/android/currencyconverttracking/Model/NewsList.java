package com.example.android.currencyconverttracking.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsList {

    @SerializedName("Data")
    private ArrayList<News> newsList;

    public ArrayList<News> getNewsArrayList() {
        return newsList;
    }

    public void setNewsArrayList(ArrayList<News> newsArrayList) {
        this.newsList = newsArrayList;
    }
}

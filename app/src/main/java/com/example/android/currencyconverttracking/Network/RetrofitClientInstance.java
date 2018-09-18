package com.example.android.currencyconverttracking.Network;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {

    private static Retrofit retrofit = null;
    private static Retrofit retrofit2 = null;
    private static Retrofit retrofit3 = null;

    private static final String BASE_URL = "https://min-api.cryptocompare.com";
    private static final String BASE_URL_CRYPTO = "https://api.coinmarketcap.com";


    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static Retrofit getCryptoData() {
        if (retrofit2 == null) {
            retrofit2 = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL_CRYPTO)
                    .addConverterFactory( GsonConverterFactory.create())
                    .build();
        }
        return retrofit2;
    }

}

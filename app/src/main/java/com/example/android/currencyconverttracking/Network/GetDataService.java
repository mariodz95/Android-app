package com.example.android.currencyconverttracking.Network;

import com.example.android.currencyconverttracking.Model.CryptocurrencyList;
import com.example.android.currencyconverttracking.Model.CurrencyRates;
import com.example.android.currencyconverttracking.Model.NewsList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface GetDataService {
    @GET("/data/v2/news/")
    Call<NewsList> getAllNews();

    @GET("/v2/ticker/?&sort=rank&structure=array")
    Call<CryptocurrencyList> getAllCrypto();

    @GET("/data/price?fsym=USD&tsyms=USD,EUR,ALL,EUR,AUD,AZN,BYN,BAM,BRL,BGN,CAD,CNY,HRK,CZK,DKK,GEL,HKD,HUF,ISK,INR,JPY,MKD,MXN,MDL,NZD,NOK,PLN,RON,RUB,RSD,SGD,ZAR,KRW,SEK,CHF,TRY,UAH,GBP,BTC,ETH,XRP,BCH,EOS,XLM,LTC,ADA,MIOTA,USDT,TRX,NEO,XMR,DASH,ETC,XEM,XTZ,BNB,VEN,OMG,ZEC,QTUM,ZRX,BCD,BCN,DCR,ZIL,ICX,BTS,ISK")
    Call<CurrencyRates> getAllRates();
}

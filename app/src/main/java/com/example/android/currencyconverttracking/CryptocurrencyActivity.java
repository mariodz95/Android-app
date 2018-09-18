package com.example.android.currencyconverttracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.currencyconverttracking.Adapter.CryptocurrencyAdapter;
import com.example.android.currencyconverttracking.Model.Cryptocurrency;
import com.example.android.currencyconverttracking.Model.CryptocurrencyList;
import com.example.android.currencyconverttracking.Network.GetDataService;
import com.example.android.currencyconverttracking.Network.RetrofitClientInstance;


import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CryptocurrencyActivity extends AppCompatActivity {


    private CryptocurrencyAdapter adapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_cryptocurrency );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getCryptoData().create(GetDataService.class);
        /*Call the method with parameter in the interface to get the crypto data*/
        Call<CryptocurrencyList> call = service.getAllCrypto();
        /*Log the URL called*/
        call.enqueue(new Callback<CryptocurrencyList>() {
            @Override
            public void onResponse(Call<CryptocurrencyList> call, Response<CryptocurrencyList> response) {
                int code = response.code();
                if (code == 200) {
                generateCryptoList(response.body().getCryptocurrencyArrayList());
            }}

            @Override
            public void onFailure(Call<CryptocurrencyList> call, Throwable t) {
                Toast.makeText(CryptocurrencyActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /*Method to generate List of crypto using RecyclerView with custom adapter*/
    private void generateCryptoList(ArrayList<Cryptocurrency> cryptoDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cryptocurrency);
        adapter = new CryptocurrencyAdapter(this,cryptoDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(CryptocurrencyActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
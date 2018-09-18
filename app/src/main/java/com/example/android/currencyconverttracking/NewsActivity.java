package com.example.android.currencyconverttracking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.currencyconverttracking.Adapter.NewsAdapter;
import com.example.android.currencyconverttracking.Model.News;
import com.example.android.currencyconverttracking.Model.NewsList;
import com.example.android.currencyconverttracking.Network.GetDataService;
import com.example.android.currencyconverttracking.Network.RetrofitClientInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    private NewsAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_news );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        /*Call the method in the interface to get the news data*/
        Call<NewsList> call = service.getAllNews();
        call.enqueue(new Callback<NewsList>() {
            @Override
            public void onResponse(Call<NewsList> call, Response<NewsList> response) {
                int code = response.code();
                if (code == 200) {
                    generateNewsList(response.body().getNewsArrayList());
            }}
            @Override
            public void onFailure(Call<NewsList> call, Throwable t) {
                Toast.makeText(NewsActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
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

    /*Method to generate List of news using RecyclerView with custom adapter*/
    private void generateNewsList(ArrayList<News> newsDataList) {
        recyclerView = (RecyclerView) findViewById(R.id.recNews);
        adapter = new NewsAdapter(this, newsDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(NewsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
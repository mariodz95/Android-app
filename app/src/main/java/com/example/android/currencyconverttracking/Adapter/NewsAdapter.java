package com.example.android.currencyconverttracking.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.android.currencyconverttracking.Model.News;
import com.example.android.currencyconverttracking.R;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<News> dataList;
    private Context context;

    public NewsAdapter(Context context,ArrayList<News> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_news, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, final int position) {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        holder.txtNewsTitle.setText(dataList.get(position).getTitle());
        holder.txtNewsBody.setText(dataList.get(position).getBody());
        holder.txtNewsDate.setText( formatter.format(new Date(dataList.get(position).getPublished_on()*1000L)));

        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getImageurl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(  holder.imgNews );


        holder.txtNewsTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = dataList.get(position).getUrl();

                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData( Uri.parse(url));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private ImageView imgNews;

        TextView txtNewsTitle, txtNewsBody, txtNewsDate;
        NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtNewsTitle =  mView.findViewById(R.id.txt_news_title);
            txtNewsBody =  mView.findViewById(R.id.txt_news_body);
            txtNewsDate = mView.findViewById(R.id.txt_news_date);
            imgNews =  mView.findViewById(R.id.img_news);
        }
    }
}
package com.example.android.currencyconverttracking.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.android.currencyconverttracking.Model.Cryptocurrency;
import com.example.android.currencyconverttracking.R;

import java.util.ArrayList;

public class CryptocurrencyAdapter extends RecyclerView.Adapter<CryptocurrencyAdapter.CryptoViewHolder> {

    private ArrayList<Cryptocurrency> dataList;
    Context context;

    public CryptocurrencyAdapter(Context context, ArrayList<Cryptocurrency> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

        @Override
        public CryptoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate( R.layout.row_cryptocurrency, parent, false);
            return new CryptoViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CryptoViewHolder holder,final int position) {

        if(dataList.get(position).getQuotes().getUSD().getPercentChange24h()>0){
            holder.txPercentChange_24h.setTextColor(Color.GREEN);
        }else{
            holder.txPercentChange_24h.setTextColor(Color.RED);
                }
        if(dataList.get(position).getQuotes().getUSD().getPercentChange7d()>0){
            holder.txPercentChange_7d.setTextColor(Color.GREEN);
        }else{
            holder.txPercentChange_7d.setTextColor(Color.RED);
        }
        if(dataList.get(position).getQuotes().getUSD().getPercentChange1h()>0){
            holder.txPercentChange_01h.setTextColor(Color.GREEN);
        }else {
            holder.txPercentChange_01h.setTextColor(Color.RED);
        }

            holder.rank.setText(dataList.get(position).getRank());
            holder.txName.setText(dataList.get(position).getName());
            holder.txShortName.setText(dataList.get(position).getSymbol());
            holder.txPrice.setText(Double.toString(dataList.get(position).getQuotes().getUSD().getPrice()));
            holder.txMarketCap.setText(Double.toString(dataList.get(position).getQuotes().getUSD().getMarketCap()));
            holder.txVolume.setText(Double.toString(dataList.get(position).getQuotes().getUSD().getVolume24h()));
            holder.txPercentChange_01h.setText("1h: " + Double.toString(dataList.get(position).getQuotes().getUSD().getPercentChange1h())+ "%");
            holder.txPercentChange_24h.setText("24h: " + Double.toString(dataList.get(position).getQuotes().getUSD().getPercentChange24h())+ "%");
            holder.txPercentChange_7d.setText("7d: " + Double.toString(dataList.get(position).getQuotes().getUSD().getPercentChange7d())+ "%");
        }


        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class CryptoViewHolder extends RecyclerView.ViewHolder {

            TextView txName, txPrice, txMarketCap,txVolume,txPercentChange_01h,txPercentChange_24h,txPercentChange_7d,txShortName,rank;
            public final View mView;
            CryptoViewHolder(View itemView) {
                super( itemView );
                mView = itemView;
                rank = (TextView) mView.findViewById( R.id.rank );
                txName = (TextView) mView.findViewById( R.id.txName );
                txShortName = (TextView) mView.findViewById( R.id.txShortName );
                txPrice = (TextView) mView.findViewById( R.id.txPrice );
                txMarketCap = (TextView) mView.findViewById( R.id.txMarketCap );
                txVolume = (TextView) itemView.findViewById( R.id.txVolume );
                txPercentChange_01h = (TextView) itemView.findViewById( R.id.txPercentChange_01h );
                txPercentChange_24h = (TextView) itemView.findViewById( R.id.txPercentChange_24h );
                txPercentChange_7d = (TextView) itemView.findViewById( R.id.txPercentChange_7d );
            }
            }
        }



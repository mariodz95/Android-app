package com.example.android.currencyconverttracking.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.currencyconverttracking.R;

public class SpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String [] currencies;
    private int[] images;

    public SpinnerAdapter(Context context, String[] currencies, int[] images) {
        super( context, R.layout.spinner_item, currencies );
        this.context = context;
        this.currencies = currencies;
        this.images = images;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View row = inflater.inflate( R.layout.spinner_item,null );
        TextView t1 = (TextView)row.findViewById( R.id.textView );
        ImageView i1 = (ImageView)row.findViewById( R.id.imageView );
        t1.setText( currencies[position] );
        i1.setImageResource( images[position] );

        return row;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View row = inflater.inflate( R.layout.spinner_item,null );
        TextView t1 = (TextView)row.findViewById( R.id.textView );
        ImageView i1 = (ImageView)row.findViewById( R.id.imageView );
        t1.setText( currencies[position] );
        i1.setImageResource( images[position] );
        return row;
    }
}

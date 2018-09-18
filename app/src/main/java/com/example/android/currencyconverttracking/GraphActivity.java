package com.example.android.currencyconverttracking;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.androidplot.Plot;
import com.androidplot.ui.SizeMode;
import com.androidplot.util.PixelUtils;
import com.androidplot.xy.BarFormatter;
import com.androidplot.xy.BoundaryMode;
import com.androidplot.xy.CatmullRomInterpolator;
import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.PointLabelFormatter;
import com.androidplot.xy.PointLabeler;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.StepMode;
import com.androidplot.xy.XYGraphWidget;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.XYSeriesRenderer;
import com.androidplot.xy.XYStepCalculator;
import com.example.android.currencyconverttracking.Adapter.SpinnerAdapter;
import com.example.android.currencyconverttracking.Network.CheckNetwork;
import com.example.android.currencyconverttracking.Network.DownloadUrl;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.FieldPosition;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class GraphActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private SimpleDateFormat sdf = new SimpleDateFormat( "d.MMM");

    private Spinner spn1;
    private Spinner spn2;
    private SpinnerAdapter adapter;
    private ImageView swap;
    private String currencyFrom, currencyTo;
    private String limit;
    private String graphData;

    ArrayList<String> time;
    ArrayList<Double> value;
    private XYPlot plot;
    int arrSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_graph );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );

        swapSpinnerSelection();
        setSpinners();

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (CheckNetwork.isInternetAvailable( GraphActivity.this )) {

                    switch (checkedId) {
                        case R.id.month:
                            limit = "30";
                            new JsonTask().execute( "https://min-api.cryptocompare.com/data/histoday?fsym=" + currencyFrom + "&tsym=" + currencyTo + "&limit=" + limit );
                            break;
                        case R.id.month3:
                            limit = "90";
                            new JsonTask().execute( "https://min-api.cryptocompare.com/data/histoday?fsym=" + currencyFrom + "&tsym=" + currencyTo + "&limit=" + limit );

                            break;
                        case R.id.year:
                            limit = "365";
                            new JsonTask().execute( "https://min-api.cryptocompare.com/data/histoday?fsym=" + currencyFrom + "&tsym=" + currencyTo + "&limit=" + limit );
                            break;
                    }
                }
            }
        });
    }

    public void setSpinners(){

        spn1 = (Spinner) findViewById(R.id.spn1);
        spn2 = (Spinner) findViewById(R.id.spn2);

        Context context= getApplicationContext();
        String[] currencies = context.getResources().getStringArray( R.array.currencies_array );
        String[] currenciesFullName = context.getResources().getStringArray( R.array.currencies_array_full_name );

        TypedArray tArray = getResources().obtainTypedArray( R.array.flags);
        int count = tArray.length();
        int[] ids = new int[count];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = tArray.getResourceId(i, 0);
        }
        tArray.recycle();

        adapter = new SpinnerAdapter( this, currencies,ids );
        spn1.setAdapter( adapter );
        spn2.setAdapter( adapter );

        spn1.setOnItemSelectedListener( this );
        spn2.setOnItemSelectedListener( this );

        String myString = "ALL"; //the value you want the position for

        ArrayAdapter myAdap = (ArrayAdapter) spn2.getAdapter(); //cast to an ArrayAdapter

        int spinnerPosition = myAdap.getPosition(myString);

        spn2.setSelection(spinnerPosition);

    }


    public void swapSpinnerSelection(){
        swap = (ImageView)findViewById( R.id.swap );
        swap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinner1Index = spn1.getSelectedItemPosition();
                spn1.setSelection(spn2.getSelectedItemPosition());
                spn2.setSelection(spinner1Index );
            }
        } );
    }


    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.spn1 || spinner.getId() == R.id.spn2)
        {
            currencyFrom = spn1.getSelectedItem().toString();
            currencyTo = spn2.getSelectedItem().toString();
            if(CheckNetwork.isInternetAvailable(GraphActivity.this)) {
                new JsonTask().execute( "https://min-api.cryptocompare.com/data/histoday?fsym=" + currencyFrom + "&tsym=" + currencyTo + "&limit=" + limit );
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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

    private class JsonTask extends AsyncTask<String, String, String> {

        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... params) {

            DownloadUrl downloadURL = new DownloadUrl();
            try {
                graphData = downloadURL.readUrl( params[0] );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return graphData;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            graphData(result);
        }


        public String graphData(String result ) {
            try {
                JSONObject object = new JSONObject( result );
                JSONArray array = object.getJSONArray( "Data" );
                arrSize = array.length();

                final Number[] value = new Number[arrSize];
                final Number[] time = new Number[arrSize];

                for (int i = 0; i < arrSize; ++i) {
                    object = array.getJSONObject( i );
                    value[i] = object.getDouble( "close" );
                    time[i] = object.getInt( "time" );
                }

                // initialize our XYPlot reference:
                plot = (XYPlot) findViewById(R.id.plot);
                plot.clear();
                // turn the above arrays into XYSeries':
                // (Y_VALS_ONLY means use the element index as the x value)
                XYSeries series1 = new SimpleXYSeries( Arrays.asList(value), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "CurrencyRate");
                // create formatters to use for drawing a series using LineAndPointRenderer
                // and configure them from xml:
                // Create a formatter to use for drawing a series using LineAndPointRenderer:
                LineAndPointFormatter series1Format = new LineAndPointFormatter(Color.rgb(50, 143, 222), null, null, null);            //
                // setup our line fill paint to be a slightly transparent gradient:
                Paint lineFill = new Paint();
                lineFill.setAlpha(200);
                lineFill.setShader(new LinearGradient(0, 0, 0, 250, Color.WHITE, Color.GREEN, Shader.TileMode.MIRROR));
                series1Format.setFillPaint(lineFill);

                plot.addSeries(series1,series1Format);
                plot.getLegend().setVisible(false);
                series1Format.setLegendIconEnabled(false);

                for(int i = 0; i < value.length; i++) {
                    if (value[i].intValue() < 1) {
                        plot.getGraph().getLineLabelStyle( XYGraphWidget.Edge.LEFT ).setFormat( new DecimalFormat( "#.#####" ) );
                    }else {
                        plot.getGraph().getLineLabelStyle( XYGraphWidget.Edge.LEFT ).setFormat( new DecimalFormat( "#" ) );
                    }
                }

                plot.getGraph().getLineLabelStyle(XYGraphWidget.Edge.BOTTOM).setFormat(new Format() {
                    @Override
                    public StringBuffer format(Object obj, StringBuffer toAppendTo, FieldPosition pos) {
                        int i = Math.round(((Number) obj).floatValue());
                        return toAppendTo.append(sdf.format(time[i].longValue()*1000));
                    }
                    @Override
                    public Object parseObject(String source, ParsePosition pos) {
                        return null;
                    }
                });
                plot.redraw();

                } catch (JSONException e) {
                e.printStackTrace();
            }
            return result;
        }




    }
}


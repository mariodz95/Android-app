package com.example.android.currencyconverttracking;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.currencyconverttracking.Adapter.SpinnerAdapter;
import com.example.android.currencyconverttracking.Model.CurrencyRates;
import com.example.android.currencyconverttracking.Network.CheckNetwork;
import com.example.android.currencyconverttracking.Network.DownloadUrl;
import com.example.android.currencyconverttracking.Network.GetDataService;
import com.example.android.currencyconverttracking.Network.RetrofitClientInstance;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private Spinner spn1;
    private Spinner spn2;
    private TextView txView;
    private EditText inputCurrency;
    private String currencyFrom, currencyTo;
    private double currencyRate;
    private double amount;
    private double res;
    private Button btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn0,btnDot;
    private ImageView imgx;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    private ImageView swap;
    private SpinnerAdapter adapter;
    ArrayList<String> listNames = new ArrayList<String>();
    ArrayList<Double> listRates = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        setUpSpinners();
        setUpKeyboardUi();
        setUpDrawer();


        swap = (ImageView)findViewById( R.id.swap );
        txView = (TextView) findViewById(R.id.txView);
        inputCurrency = (EditText) findViewById(R.id.inputCurrency);
        inputCurrency.setFocusable(false);
        inputCurrency.setSelection(inputCurrency.getText().length());

        onEditTextChange();
        swapSpinnerSelection();

        /*Create handle for the RetrofitInstance interface*/
        GetDataService service1 = RetrofitClientInstance.getRetrofitInstance().create( GetDataService.class );
        /*Call the method with parameter in the interface to get the crypto data*/
        Call<CurrencyRates> callasd = service1.getAllRates();

        callasd.enqueue( new Callback<CurrencyRates>() {
            @Override
            public void onResponse(Call<CurrencyRates> call, Response<CurrencyRates> response) {
                int code = response.code();
                if (code == 200) {
                    cryptocompareRates("EUR",response.body().getEUR());
                    cryptocompareRates("ALL",response.body().getALL());
                    cryptocompareRates("USD",response.body().getUSD());
                    cryptocompareRates("AUD",response.body().getAUD());
                    cryptocompareRates("AZN",response.body().getAZN());
                    cryptocompareRates("BYN",response.body().getBYN());
                    cryptocompareRates("BAM",response.body().getBAM());
                    cryptocompareRates("BRL",response.body().getBRL());
                    cryptocompareRates("BGN",response.body().getBGN());
                    cryptocompareRates("CAD",response.body().getCAD());
                    cryptocompareRates("CNY",response.body().getCNY());
                    cryptocompareRates("HRK",response.body().getHRK());
                    cryptocompareRates("CZK",response.body().getCZK());
                    cryptocompareRates("DKK",response.body().getDKK());
                    cryptocompareRates("GEL",response.body().getGEL());
                    cryptocompareRates("HKD",response.body().getHKD());
                    cryptocompareRates("HUF",response.body().getHUF());
                    cryptocompareRates("ISK",response.body().getISK());
                    cryptocompareRates("INR",response.body().getINR());
                    cryptocompareRates("JPY",response.body().getJPY());
                    cryptocompareRates("MXN",response.body().getMXN());
                    cryptocompareRates("MDL",response.body().getMDL());
                    cryptocompareRates("NZD",response.body().getNZD());
                    cryptocompareRates("NOK",response.body().getNOK());
                    cryptocompareRates("PLN",response.body().getPLN());
                    cryptocompareRates("RON",response.body().getRON());
                    cryptocompareRates("RUB",response.body().getRUB());
                    cryptocompareRates("RSD",response.body().getRSD());
                    cryptocompareRates("SGD",response.body().getSGD());
                    cryptocompareRates("ZAR",response.body().getZAR());
                    cryptocompareRates("KRW",response.body().getKRW());
                    cryptocompareRates("SEK",response.body().getSEK());
                    cryptocompareRates("CHF",response.body().getCHF());
                    cryptocompareRates("TRY",response.body().getTRY());
                    cryptocompareRates("UAH",response.body().getUAH());
                    cryptocompareRates("GBP",response.body().getGBP());
                    cryptocompareRates("BTC",response.body().getBTC());
                    cryptocompareRates("ETH",response.body().getETH());
                    cryptocompareRates("XRP",response.body().getXRP());
                    cryptocompareRates("BCH",response.body().getBCH());
                    cryptocompareRates("EOS",response.body().getEOS());
                    cryptocompareRates("XLM",response.body().getXLM());
                    cryptocompareRates("LTC",response.body().getLTC());
                    cryptocompareRates("ADA",response.body().getADA());
                    cryptocompareRates("USDT",response.body().getUSDT());
                    cryptocompareRates("TRX",response.body().getTRX());
                    cryptocompareRates("NEO",response.body().getNEO());
                    cryptocompareRates("XMR",response.body().getXMR());
                    cryptocompareRates("DASH",response.body().getDASH());
                    cryptocompareRates("ETC",response.body().getETC());
                    cryptocompareRates("XEM",response.body().getXEM());
                    cryptocompareRates("XTZ",response.body().getXTZ());
                    cryptocompareRates("BNB",response.body().getBNB());
                    cryptocompareRates("VEN",response.body().getVEN());
                    cryptocompareRates("OMG",response.body().getOMG());
                    cryptocompareRates("ZEC",response.body().getZEC());
                    cryptocompareRates("QTUM",response.body().getQTUM());
                    cryptocompareRates("ZRX",response.body().getZRX());
                    cryptocompareRates("BCD",response.body().getBCD());
                    cryptocompareRates("BCN",response.body().getBCN());
                    cryptocompareRates("DCR",response.body().getDCR());
                    cryptocompareRates("ZIL",response.body().getZIL());
                    cryptocompareRates("ICX",response.body().getICX());
                    cryptocompareRates("BTS",response.body().getBTS());
                    cryptocompareRates("ISK",response.body().getISK());

                }
            }

            @Override
            public void onFailure(Call<CurrencyRates> call, Throwable t) {
                Toast.makeText( MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT ).show();
            }
        } );



    }



    public void setUpSpinners(){

        spn1 = (Spinner) findViewById(R.id.spn1);
        spn2 = (Spinner) findViewById(R.id.spn2);
        //It allows access to application-specific resources
        Context context = getApplicationContext();
        String[] currencies = context.getResources().getStringArray( R.array.currencies_array );

        //container for an array of values that were retrieved with Resources
        TypedArray tArray = getResources().obtainTypedArray( R.array.flags);
        int count = tArray.length();
        int[] flags = new int[count];
        for (int i = 0; i < flags.length; i++) {
            flags[i] = tArray.getResourceId(i, 0);
        }
        //The indices used to retrieve values from this structure correspond to the positions of the attributes given to obtainStyledAttributes.
        tArray.recycle();

        adapter = new SpinnerAdapter( this, currencies,flags );
        spn1.setAdapter( adapter );
        spn2.setAdapter( adapter );

        spn1.setOnItemSelectedListener( this );
        spn2.setOnItemSelectedListener( this );

    }

    private void cryptocompareRates(String names, Double rates){
        listNames.add( names );
        listRates.add( rates );
    }


    public void calculate(){
        Double fromCurrency = 0.0;
        Double toCurrency = 0.0;

        for (int i = 0; i < listNames.size(); i++) {
            if (listNames.get( i ).equals( currencyFrom )) {
                fromCurrency = listRates.get( i );
            }
        }

        for (int i = 0; i < listNames.size(); i++) {
            if (listNames.get( i ).equals( currencyTo )) {
                toCurrency = listRates.get( i );
            }
        }
        if( inputCurrency.getText().toString().trim().length() == 0 || inputCurrency.getText().toString().equals(".")) {
            int res = 0;
            txView.setText( Double.toString( res ) );
        }else{
            Double amount = Double.parseDouble(inputCurrency.getText().toString());
            res = amount * ((1 /  fromCurrency ) *  toCurrency);
            String result = Double.toString(round(res,4));
            txView.setText(result);
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public void setUpKeyboardUi(){

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.btn4);
        btn5 = (Button) findViewById(R.id.btn5);
        btn6 = (Button) findViewById(R.id.btn6);
        btn7 = (Button) findViewById(R.id.btn7);
        btn8 = (Button) findViewById(R.id.btn8);
        btn9 = (Button) findViewById(R.id.btn9);
        btn0 = (Button) findViewById(R.id.btn0);
        imgx = (ImageView) findViewById(R.id.imgx);
        btnDot = (Button) findViewById(R.id.btnDot);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        imgx.setOnClickListener(this);
        btnDot.setOnClickListener(this);
    }


    public void setUpDrawer(){
        nv = (NavigationView)findViewById(R.id.nv);
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawer();

    }
    public void onEditTextChange(){
        inputCurrency.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!s.equals("") )
                {
                    calculate();
                }
                }
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }
                public void afterTextChanged(Editable s) {
                }
            });
        }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Spinner spinner = (Spinner) parent;
            if(spinner.getId() == R.id.spn1 || spinner.getId() == R.id.spn2)
            {
                if(CheckNetwork.isInternetAvailable(MainActivity.this))
                {
                    currencyFrom = spn1.getSelectedItem().toString();
                    currencyTo = spn2.getSelectedItem().toString();
//                    new JsonTask().execute("https://min-api.cryptocompare.com/data/price?fsym="+currencyFrom+"&tsyms="+currencyTo);
                    calculate();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"No Internet Connection!",Toast.LENGTH_LONG).show();
                }
            }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void drawer(){
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if(CheckNetwork.isInternetAvailable(MainActivity.this)) {
                    switch (id) {
                        case R.id.graph:
                            Intent graphIntent = new Intent( MainActivity.this, GraphActivity.class );
                            MainActivity.this.startActivity( graphIntent );
                            break;
                        case R.id.news:
                            Intent newsIntent = new Intent( MainActivity.this, NewsActivity.class );
                            MainActivity.this.startActivity( newsIntent );
                            break;
                        case R.id.map:
                            Intent mapIntent = new Intent( MainActivity.this, MapsActivity.class );
                            MainActivity.this.startActivity( mapIntent );
                            break;
                        case R.id.cryptocurrency:
                            Intent cryptocurrencyIntent = new Intent( MainActivity.this, CryptocurrencyActivity.class );
                            MainActivity.this.startActivity( cryptocurrencyIntent );
                            break;
                        default:
                    }
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    public void calculateConversion(){
        if( inputCurrency.getText().toString().trim().length() == 0 || inputCurrency.getText().toString().equals(".")) {
            res = 0;
            txView.setText( Double.toString( res ) );
        }else{
            amount = Double.parseDouble(inputCurrency.getText().toString());
            res = currencyRate * amount;
            txView.setText(Double.toString(  res ));
        }
    }

    public void swapSpinnerSelection(){
        swap.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int spinner1Index = spn1.getSelectedItemPosition();
                spn1.setSelection(spn2.getSelectedItemPosition());
                spn2.setSelection(spinner1Index );
            }
        } );
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn1:
                inputCurrency.setText(inputCurrency.getText().toString()+btn1.getText().toString());
                break;
            case R.id.btn2:
                inputCurrency.setText(inputCurrency.getText().toString()+btn2.getText().toString());
                break;
            case R.id.btn3:
                inputCurrency.setText(inputCurrency.getText().toString()+btn3.getText().toString());
                break;
            case R.id.btn4:
                inputCurrency.setText(inputCurrency.getText().toString()+btn4.getText().toString());
                break;
            case R.id.btn5:
                inputCurrency.setText(inputCurrency.getText().toString()+btn5.getText().toString());
                break;
            case R.id.btn6:
                inputCurrency.setText(inputCurrency.getText().toString()+btn6.getText().toString());
                break;
            case R.id.btn7:
                inputCurrency.setText(inputCurrency.getText().toString()+btn7.getText().toString());
                break;
            case R.id.btn8:
                inputCurrency.setText(inputCurrency.getText().toString()+btn8.getText().toString());
                break;
            case R.id.btn9:
                inputCurrency.setText(inputCurrency.getText().toString()+btn9.getText().toString());
                break;
            case R.id.btn0:
                inputCurrency.setText(inputCurrency.getText().toString()+btn0.getText().toString());
                break;
            case R.id.imgx:
                String text = inputCurrency.getText().toString();
                if(!TextUtils.isEmpty(text)) {
                    String newText1 = text.substring(0, text.length() - 1); //delete from right
                    inputCurrency.setText(newText1);
                    inputCurrency.setSelection(newText1.length());
                }
                break;
            case R.id.btnDot:
                if( inputCurrency.getText().toString().trim().length() == 0 || inputCurrency.getText().toString().contains(".")) {

                }else{
                inputCurrency.setText( inputCurrency.getText().toString() + btnDot.getText().toString() );
            }
                break;
            default:
                break;
        }
    }


//
//    public class JsonTask extends AsyncTask<String, String, String> {
//
//        protected void onPreExecute() {
//            super.onPreExecute();
//        }
//
//        protected String doInBackground(String... params) {
//
//            DownloadUrl downloadURL = new DownloadUrl();
//            try {
//                currencyRates = downloadURL.readUrl( params[0] );
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return currencyRates;
//        }
//
//        @Override
//        protected void onPostExecute(String result) {
//            super.onPostExecute(result);
//            currencyValue(result);
//        }
//
//        private String currencyValue(String result ) {
//            try {
//                JSONObject reader = new JSONObject(result);
//                currencyRate = reader.getDouble(currencyTo);
//                calculateConversion();
//            } catch (JSONException e) {
//               e.printStackTrace();
//            }
//            return result;
//        }
//    }
}

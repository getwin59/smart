package com.atilsamancioglu.currencyconverter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    TextView textView2;
    TextView textView3;
    EditText editText;
    Button getButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView3 = (TextView) findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText);
        getButton = (Button) findViewById(R.id.button);

        getButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRates();
            }
        });

    }

    public void getRates() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openexchangerates.org/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RateAPI service = retrofit.create(RateAPI.class);
        Call<RateResponse> rate = service.getRates();
        rate.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                RateResponse jsonObject = response.body();
                RateModel rateModel = jsonObject.getRatesObject();
                textView.setText(rateModel.getTryValue() + " ₺");
                textView2.setText(rateModel.getEurValue() + " €");
                textView3.setText(rateModel.getBtcValue() + " BTC");
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Log.i("Test", call.request().toString());
            }
        });
    }


    private class DownloadData extends AsyncTask<String, Void, String> {

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {

                JSONObject jsonObject = new JSONObject(s);
                String base = jsonObject.getString("base");
                System.out.println(base);
                String date = jsonObject.getString("date");
                System.out.println(date);
                String rates = jsonObject.getString("rates");
                System.out.println(rates);

                JSONObject jsonObject1 = new JSONObject(rates);
                String usd = jsonObject1.getString("USD");
                System.out.println(usd);
                String eur = jsonObject1.getString("EUR");
                System.out.println(eur);
                String chf = jsonObject1.getString("CHF");

                textView.setText("USD: " + usd);
                textView2.setText("EUR: " + eur);
                textView3.setText("CHF: " + chf);


            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        @Override
        protected String doInBackground(String... params) {

            String result = "";
            URL url;
            HttpURLConnection httpURLConnection;

            try {

                url = new URL(params[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

                int data = inputStreamReader.read();

                while (data > 0) {

                    char character = (char) data;
                    result += character;

                    data = inputStreamReader.read();
                }

                return result;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }


        }
    }


}



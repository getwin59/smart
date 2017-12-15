package com.atilsamancioglu.currencyconverter;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Eray on 15.12.2017.
 */

public interface RateAPI {
    @GET("latest")
    Call<RateResponse> getRates();
}

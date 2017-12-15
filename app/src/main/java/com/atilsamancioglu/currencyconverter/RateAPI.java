package com.atilsamancioglu.currencyconverter;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Eray on 15.12.2017.
 */

public interface RateAPI {
    @GET("latest.json?app_id=28146f4b30f54e368f026c22c9e45316")
    Call<RateResponse> getRates();
}

package com.atilsamancioglu.currencyconverter;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eray on 15.12.2017.
 */

public class RateResponse {
    @SerializedName("base")
    String base;

    @SerializedName("rates")
    JSONObject ratesObject;

    public String getBase() {
        return base;
    }

    public double getTRY() throws JSONException {
        return ratesObject.getDouble("TRY");
    }
}

package com.atilsamancioglu.currencyconverter;

import com.google.gson.annotations.SerializedName;

import org.json.JSONException;

/**
 * Created by Eray on 15.12.2017.
 */

public class RateResponse {
    @SerializedName("base")
    String base;

    @SerializedName("license")
    String license;

    @SerializedName("rates")
    RateModel ratesObject;

    public String getBase() {
        return base;
    }

    public String getLicense() {
        return license;
    }

    public RateModel getRatesObject() {
        return ratesObject;
    }

    public double getTRY() throws JSONException {
        return ratesObject.getTryValue();
    }
}

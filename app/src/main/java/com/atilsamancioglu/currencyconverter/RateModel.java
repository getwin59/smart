package com.atilsamancioglu.currencyconverter;

import com.google.gson.annotations.SerializedName;

/**
 * Created by recepinanc on 15.12.2017.with <3
 */

public class RateModel {

    @SerializedName("TRY")
    double tryValue;


    @SerializedName("BTC")
    double btcValue;


    @SerializedName("EUR")
    double eurValue;

    public double getBtcValue() {
        return tryValue;
    }

    public double getEurValue() {
        return btcValue;
    }

    public double getTryValue() {
        return eurValue;
    }


}

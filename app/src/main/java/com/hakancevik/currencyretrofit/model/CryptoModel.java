package com.hakancevik.currencyretrofit.model;

import com.google.gson.annotations.SerializedName;

public class CryptoModel {

    @SerializedName("currency")
    public String currency;

    @SerializedName("name")
    public String name;

    @SerializedName("logo_url")
    public String logo_url;

    @SerializedName("price")
    public String price;


}

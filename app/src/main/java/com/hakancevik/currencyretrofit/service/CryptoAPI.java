package com.hakancevik.currencyretrofit.service;

import com.hakancevik.currencyretrofit.model.CryptoModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    // GET, POST, UPDATE, DELETE

    // URL BASE --> https://api.nomics.com/v1/
    // GET --> currencies/ticker?key=d1587f1fca87cced047e2de0fd02fe37978520e1

    @GET("currencies/ticker?key=d1587f1fca87cced047e2de0fd02fe37978520e1")
    Call<List<CryptoModel>> getData();





}

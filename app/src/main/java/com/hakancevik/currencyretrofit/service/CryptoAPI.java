package com.hakancevik.currencyretrofit.service;

import com.hakancevik.currencyretrofit.model.CryptoModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface CryptoAPI {

    // GET, POST, UPDATE, DELETE

    // URL BASE --> https://api.nomics.com/v1/
    // GET --> currencies/ticker?key=d1587f1fca87cced047e2de0fd02fe37978520e1

    // URL BASE --> https://raw.githubusercontent.com/Cevik10/
    // GET --> AS10-CryptoCurrencyRetrofit/master/sparecrypto.json


    @GET("AS10-CryptoCurrencyRetrofit/master/sparecrypto.json")
    Observable<List<CryptoModel>> getData();
    //Call<List<CryptoModel>> getData();


}

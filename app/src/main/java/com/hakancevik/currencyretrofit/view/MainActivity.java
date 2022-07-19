package com.hakancevik.currencyretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hakancevik.currencyretrofit.adapter.CryptoAdapter;
import com.hakancevik.currencyretrofit.databinding.ActivityMainBinding;
import com.hakancevik.currencyretrofit.model.CryptoModel;
import com.hakancevik.currencyretrofit.service.CryptoAPI;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ArrayList<CryptoModel> cryptoModels;
    private final String BASE_URL = "https://api.nomics.com/v1/";

    Retrofit retrofit;
    Gson gson;

    CryptoAdapter cryptoAdapter;

    CompositeDisposable compositeDisposable;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // https://api.nomics.com/v1/currencies/ticker?key=d1587f1fca87cced047e2de0fd02fe37978520e1
        // https://raw.githubusercontent.com/Cevik10/AS10-CryptoCurrencyRetrofit/master/sparecrypto.json


        // Retrofit & JSON

        gson = new GsonBuilder().setLenient().create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        loadData();


    }

    private void loadData() {

        final CryptoAPI cryptoAPI = retrofit.create(CryptoAPI.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(cryptoAPI.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));




        /*
        Call<List<CryptoModel>> call = cryptoAPI.getData();
        call.enqueue(new Callback<List<CryptoModel>>() {
            @Override
            public void onResponse(Call<List<CryptoModel>> call, Response<List<CryptoModel>> response) {
                if (response.isSuccessful()){
                    List<CryptoModel> responseList = response.body();
                    cryptoModels = new ArrayList<>(responseList);

                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    cryptoAdapter = new CryptoAdapter(cryptoModels);
                    binding.recyclerView.setAdapter(cryptoAdapter);


//                    for (CryptoModel cryptoModel : cryptoModels){
//
//                        System.out.println(cryptoModel.currency);
//                        System.out.println(cryptoModel.price);
//                        System.out.println(cryptoModel.logo_url);
//                        System.out.println(cryptoModel.name);
//                    }



                }
            }

            @Override
            public void onFailure(Call<List<CryptoModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

         */


    }

    private void handleResponse(List<CryptoModel> cryptoModelList) {

        cryptoModels = new ArrayList<>(cryptoModelList);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        cryptoAdapter = new CryptoAdapter(cryptoModels);
        binding.recyclerView.setAdapter(cryptoAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}
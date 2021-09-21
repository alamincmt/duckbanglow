package com.duckbanglowofficial.duckbanglow.services.network;

import android.service.dreams.DreamService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

//    public static String BASE_URL = "http://duckbanglow.com/api";
    public static String BASE_URL = "https://www.colorceramics.com/api/v1/";
    public static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){

        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;

    }

}

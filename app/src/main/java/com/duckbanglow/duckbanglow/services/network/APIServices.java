package com.duckbanglow.duckbanglow.services.network;

import com.duckbanglow.duckbanglow.services.models.Division;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIServices {

    @GET("divisions")
    Call<Division> getDivisionList();

}

package com.duckbanglowofficial.duckbanglow.services.models.temp;


import retrofit2.Call;
import retrofit2.http.GET;

public interface LocationApiEndpointInterface {
    @GET("divisions")
    Call<MainDivisions> getAllDivisions();

    @GET("district")
    Call<MainDistrict> getAllDistricts();

    @GET("thana")
    Call<MainThana> getAllThana();
}

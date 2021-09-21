package com.duckbanglowofficial.duckbanglow.services.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.duckbanglowofficial.duckbanglow.services.models.Division;
import com.duckbanglowofficial.duckbanglow.services.models.DivisionData;
import com.duckbanglowofficial.duckbanglow.services.network.APIServices;
import com.duckbanglowofficial.duckbanglow.services.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DivisionRepositoryImpl implements DivisionRepository {

    private static Context mContext;
    private static DivisionRepositoryImpl divisionRepositoryInstance;
    private static Division division;
    private List<DivisionData> divisionList;
    private MutableLiveData mutableLiveData;

    public static DivisionRepository getInstance(Context context){

        if(divisionRepositoryInstance == null){
            mContext = context;
            divisionRepositoryInstance = new DivisionRepositoryImpl();
        }

        return divisionRepositoryInstance;
    }


    @Override
    public MutableLiveData<List<DivisionData>> getDivisionList() {
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData();
        }

        APIServices apiServices = RetrofitInstance.getRetrofitInstance().create(APIServices.class);
        Call<Division> call = apiServices.getDivisionList();
        call.enqueue(new Callback<Division>() {
            @Override
            public void onResponse(Call<Division> call, Response<Division> response) {
                division = response.body();
                divisionList = division.getDivisionList();
                mutableLiveData.postValue(divisionList);
            }

            @Override
            public void onFailure(Call<Division> call, Throwable t) {
                Log.d("AlAmin", "Debug: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }
}

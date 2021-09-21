package com.duckbanglowofficial.duckbanglow.views.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.duckbanglowofficial.duckbanglow.R;
import com.duckbanglowofficial.duckbanglow.services.models.temp.District;
import com.duckbanglowofficial.duckbanglow.services.models.temp.Divisions;
import com.duckbanglowofficial.duckbanglow.services.models.temp.MainDistrict;
import com.duckbanglowofficial.duckbanglow.services.models.temp.MainDivisions;
import com.duckbanglowofficial.duckbanglow.services.models.temp.MainThana;
import com.duckbanglowofficial.duckbanglow.services.models.temp.RetrofitClient;
import com.duckbanglowofficial.duckbanglow.services.models.temp.Thana;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboardActivity extends AppCompatActivity {
    private List<Divisions> divisionList = new ArrayList<>();
    private List<District> districtList = new ArrayList<>();
    private List<Thana> thanaList = new ArrayList<>();

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        textView = findViewById(R.id.my_name);

        getDivision();
//        getDistrict();
//        getThana();


    }

    private void getDivision() {
        Call<MainDivisions> divisionListCall = RetrofitClient.getInstance().getApiInterface().getAllDivisions();
        divisionListCall.enqueue(new Callback<MainDivisions>() {
            @Override
            public void onResponse(Call<MainDivisions> call, Response<MainDivisions> response) {
                assert response.body() != null;
                divisionList = response.body().getData();
                Log.d("divisionList",""+divisionList);

            }
            @Override
            public void onFailure(Call<MainDivisions> call, Throwable t) {
                Log.d("call",""+t.getMessage());
            }
        });
    }

    /*private void getDistrict(){
        Call<MainDistrict> districtListCall = RetrofitClient.getInstance().getAllDistrict().getAllDistricts();
        districtListCall.enqueue(new Callback<MainDistrict>() {
            @Override
            public void onResponse(Call<MainDistrict> call, Response<MainDistrict> response) {
                districtList = response.body().getData();
                Log.d("districtList",""+districtList);
            }
            @Override
            public void onFailure(Call<MainDistrict> call, Throwable t) {

            }
        });
    }
    private void getThana(){
        Call<MainThana> thanaListCall = RetrofitClient.getInstance().getAllThana().getAllThana();
        thanaListCall.enqueue(new Callback<MainThana>() {
            @Override
            public void onResponse(Call<MainThana> call, Response<MainThana> response) {
                thanaList = response.body().getData();
                Log.d("thanaList",""+thanaList);

            }
            @Override
            public void onFailure(Call<MainThana> call, Throwable t) {

            }
        });
    }*/
}
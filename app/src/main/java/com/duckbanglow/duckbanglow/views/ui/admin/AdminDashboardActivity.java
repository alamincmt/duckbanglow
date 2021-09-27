package com.duckbanglow.duckbanglow.views.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.duckbanglow.duckbanglow.R;
import com.duckbanglow.duckbanglow.services.models.temp.District;
import com.duckbanglow.duckbanglow.services.models.temp.Divisions;
import com.duckbanglow.duckbanglow.services.models.temp.MainDivisions;
import com.duckbanglow.duckbanglow.services.models.temp.RetrofitClient;
import com.duckbanglow.duckbanglow.services.models.temp.Thana;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminDashboardActivity extends AppCompatActivity {
    private List<Divisions> divisionList = new ArrayList<>();
    private List<District> districtList = new ArrayList<>();
    private List<Thana> thanaList = new ArrayList<>();

    TextView add_food,add_hotel,add_vehicle;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        add_food = findViewById(R.id.add_food);
        add_hotel = findViewById(R.id.add_hotel);
        add_vehicle = findViewById(R.id.add_vehicle);

        add_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboardActivity.this,FoodAddActivity.class);
                startActivity(intent);
            }
        });
        add_hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboardActivity.this,HotelAddActivity.class);
                startActivity(intent);
            }
        });
        add_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminDashboardActivity.this,VehicleAddActivity.class);
                startActivity(intent);
            }
        });


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
package com.duckbanglowofficial.duckbanglow.views.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.duckbanglowofficial.duckbanglow.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        findViewById(R.id.car_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity("CarService");
            }
        });

        findViewById(R.id.food_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity("Food Service");
            }
        });

        findViewById(R.id.room_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity("Room Service");
            }
        });
    }
    void  nextActivity(String purpose){
        Intent intent = new Intent(ServiceActivity.this,ServiceDetailsActivity.class);
        intent.putExtra("purpose",purpose);
        startActivity(intent);
    }
}
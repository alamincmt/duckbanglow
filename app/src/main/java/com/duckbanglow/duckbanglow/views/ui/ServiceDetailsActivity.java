package com.duckbanglow.duckbanglow.views.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.duckbanglow.duckbanglow.R;

public class ServiceDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
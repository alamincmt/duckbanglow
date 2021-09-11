package com.duckbanglowofficial.duckbanglow.views.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglowofficial.duckbanglow.R;

public class SplashScreenActivity extends AppCompatActivity {

    private ProgressBar progressbar;
    private TextView tv_copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressbar=findViewById(R.id.progressbar);
        tv_copyright=findViewById(R.id.tv_copyright);

        progressbar.setVisibility(View.VISIBLE);


        tv_copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("http://shikkhabondhu.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onResume() {

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);

        progressbar.setVisibility(View.GONE);

        super.onResume();
    }
}
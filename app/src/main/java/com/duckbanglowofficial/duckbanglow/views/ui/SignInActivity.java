package com.duckbanglowofficial.duckbanglow.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglowofficial.duckbanglow.R;

public class SignInActivity extends AppCompatActivity {

    private EditText et_username,et_password;
    private Button btn_skip,btn_signin;
    private TextView tv_signup_for_anaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        btn_skip=findViewById(R.id.btn_skip);
        btn_signin=findViewById(R.id.btn_signin);
        tv_signup_for_anaccount=findViewById(R.id.tv_signup_for_anaccount);

        btn_skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });

        tv_signup_for_anaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUpActivity.class));
                finish();
            }
        });
    }
}
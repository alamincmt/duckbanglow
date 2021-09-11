package com.duckbanglowofficial.duckbanglow.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglowofficial.duckbanglow.R;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_fullname,et_username,et_password;
    private RadioGroup gender;
    private Button btn_register,btn_register_with_facebook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        et_fullname=findViewById(R.id.et_fullname);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        gender=findViewById(R.id.gender);
        btn_register=findViewById(R.id.btn_register);
        btn_register_with_facebook=findViewById(R.id.btn_register_with_facebook);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });




    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),SignInActivity.class));
        finish();
    }
}
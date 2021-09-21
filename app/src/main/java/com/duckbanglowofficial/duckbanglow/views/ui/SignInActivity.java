package com.duckbanglowofficial.duckbanglow.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglowofficial.duckbanglow.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {

    private EditText et_user_email,et_password;
    private Button btn_skip,btn_signin;
    private TextView tv_signup_for_anaccount;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        firebaseAuth = FirebaseAuth.getInstance();
        et_user_email=findViewById(R.id.et_user_email);
        et_password=findViewById(R.id.et_password);
        btn_skip=findViewById(R.id.btn_skip);
        btn_signin=findViewById(R.id.btn_signin);
        tv_signup_for_anaccount=findViewById(R.id.tv_signup_for_anaccount);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = et_user_email.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (email.length()>10 && password.length()>5)
                    firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent intent = new Intent(SignInActivity.this,MainActivity.class);
                            startActivity(intent);
                            Toast.makeText(SignInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(SignInActivity.this, "Sign In Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull @NotNull Exception e) {
                        Toast.makeText(SignInActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(SignInActivity.this, "Login Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
                else
                    Toast.makeText(SignInActivity.this, "Email or password is wrong", Toast.LENGTH_SHORT).show();

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
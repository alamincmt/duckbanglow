package com.duckbanglow.duckbanglow.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglow.duckbanglow.R;
import com.duckbanglow.duckbanglow.views.ui.admin.AdminDashboardActivity;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class SignInActivity extends AppCompatActivity {

    private EditText et_user_email,et_password;
    private Button btn_skip,btn_signin;
    private TextView tv_signup_for_anaccount;

    DatabaseReference databaseReference;

    String adminEmail = "admin@duckbanglow.com";
    String adminPass = "DB#321";


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        databaseReference = FirebaseDatabase.getInstance().getReference("Admin");

        firebaseAuth = FirebaseAuth.getInstance();
        et_user_email=findViewById(R.id.et_user_email);
        et_password=findViewById(R.id.et_password);
        btn_skip=findViewById(R.id.btn_skip);
        btn_signin=findViewById(R.id.btn_signin);
        tv_signup_for_anaccount=findViewById(R.id.tv_signup_for_anaccount);
/*

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                adminEmail = String.valueOf(snapshot.child("username").getValue());
                adminPass = String.valueOf(snapshot.child("password").getValue());

                Log.d("adminEmail",adminEmail);
                Log.d("adminPass",adminPass);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("Error",error.getMessage().toString());

            }
        });

*/

        et_user_email.setText(adminEmail);
        et_password.setText(adminPass);
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
                            finish();
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

  if (adminEmail.equals(email) && adminPass.equals(password)){
                    Intent intent = new Intent(SignInActivity.this, AdminDashboardActivity.class);
                    startActivity(intent);
                }

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
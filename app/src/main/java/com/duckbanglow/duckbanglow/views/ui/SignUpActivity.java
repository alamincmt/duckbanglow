package com.duckbanglow.duckbanglow.views.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.duckbanglow.duckbanglow.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private EditText et_fullname,et_username,et_phone,et_nid,et_password,et_address,et_shortdescription,nid;
    private RadioGroup gender;
    private RadioButton selectedGender;
    private Button btn_register,btn_register_with_facebook;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        et_fullname=findViewById(R.id.et_fullname);
        et_username=findViewById(R.id.et_username);
        et_phone=findViewById(R.id.et_phone);
        et_nid=findViewById(R.id.et_nid);
        et_password=findViewById(R.id.et_password);
        et_address=findViewById(R.id.et_address);
        et_shortdescription=findViewById(R.id.et_shortdescription);
        gender=findViewById(R.id.gender);
        btn_register=findViewById(R.id.btn_register);
        btn_register_with_facebook=findViewById(R.id.btn_register_with_facebook);
        nid=findViewById(R.id.nid);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int selectedId = gender.getCheckedRadioButtonId();
                selectedGender = (RadioButton) findViewById(selectedId);

                String fullName = et_fullname.getText().toString().trim();
                String userEmail= et_username.getText().toString().trim();
                String phoneNumber= et_phone.getText().toString().trim();
                String nidNumber= et_nid.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                String gender =  String.valueOf(selectedGender.getText());
                String address =  et_address.getText().toString().trim();
                String shortDescription =  et_shortdescription.getText().toString().trim();

                if (fullName.length()>2 && userEmail.length()>10 && password.length()>5){
                    firebaseAuth.createUserWithEmailAndPassword(userEmail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                HashMap<String,String> userMap = new HashMap<>();
                                userMap.put("fullName",fullName);
                                userMap.put("userEmail",userEmail);
                                userMap.put("phoneNumber",phoneNumber);
                                userMap.put("nidNumber",nidNumber);
                                userMap.put("password",password);
                                userMap.put("gender",gender);
                                userMap.put("address",address);
                                userMap.put("profile_image","");
                                userMap.put("shortDescription",shortDescription);
                                userMap.put("id",firebaseAuth.getCurrentUser().getUid());
                                mDatabase.child(firebaseAuth.getCurrentUser().getUid()).setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<Void> task) {
                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(SignUpActivity.this,MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull @NotNull Exception e) {
                            Toast.makeText(SignUpActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(SignUpActivity.this, "Sign Up Cancel", Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    Toast.makeText(SignUpActivity.this, "Please fill up all the information first. ", Toast.LENGTH_LONG).show();
                }
            }
        });




    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
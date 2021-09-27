package com.duckbanglow.duckbanglow.views.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.duckbanglow.duckbanglow.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class VehicleAddActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    EditText edt_Purpose_name,edt_Purpose_desc,edt_Purpose_phone,edt_Purpose_address,edt_Purpose_bedroom,edt_Purpose_bathroom,edt_Purpose_area,edt_Purpose_price,et_short_address;
    Spinner sp_destrict;
    Button btn_sub;

    String propertyName = "";
    String propertyDes = "";
    String propertyPhone = "";
    String propertyAddress = "";
    String propertyBedRoom = "";
    String propertyBathRoom = "";
    String propertyArea = "";
    String propertyPrice = "";
    String district = "" ;
    String shortAddress = "";
    String id = "";
    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_add);


        databaseReference = FirebaseDatabase.getInstance().getReference("Vehicles");
        id = databaseReference.getKey().toString();

        edt_Purpose_name = findViewById(R.id.edt_Purpose_name);
        edt_Purpose_desc = findViewById(R.id.edt_Purpose_desc);
        edt_Purpose_phone = findViewById(R.id.edt_Purpose_phone);
        edt_Purpose_address = findViewById(R.id.edt_Purpose_address);
        edt_Purpose_bedroom = findViewById(R.id.edt_Purpose_bedroom);
        edt_Purpose_bathroom = findViewById(R.id.edt_Purpose_bathroom);
        edt_Purpose_area = findViewById(R.id.edt_Purpose_area);
        edt_Purpose_price = findViewById(R.id.edt_Purpose_price);
        et_short_address = findViewById(R.id.et_short_address);
        sp_destrict = findViewById(R.id.sp_destrict);
        btn_sub = findViewById(R.id.btn_sub);


        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bd_district));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_destrict.setAdapter(spinnerArrayAdapter);


        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                propertyName = edt_Purpose_name.getText().toString().trim();
                propertyDes = edt_Purpose_desc.getText().toString().trim();
                propertyPhone = edt_Purpose_phone.getText().toString().trim();
                propertyAddress = edt_Purpose_address.getText().toString().trim();
                propertyBedRoom = edt_Purpose_bedroom.getText().toString().trim();
                propertyBathRoom = edt_Purpose_bathroom.getText().toString().trim();
                propertyArea = edt_Purpose_area.getText().toString().trim();
                propertyPrice = edt_Purpose_price.getText().toString().trim();
                shortAddress = et_short_address.getText().toString().trim();

                HashMap<String, String> addMap = new HashMap<>();
                if (propertyName.length()>1 && propertyDes.length()>1 && propertyPhone.length()>1 && propertyAddress.length()>1 && propertyBedRoom.length()>1 && propertyBathRoom.length()>1 && propertyArea.length()>1 && shortAddress.length()>1){
                    addMap.put("id",id);
                    addMap.put("VehicleName",propertyName);
                    addMap.put("VehicleDes",propertyDes);
                    addMap.put("vehiclePhone",propertyPhone);
                    addMap.put("vehicleAddress",propertyAddress);
                    addMap.put("vehicleBedRoom",propertyBedRoom);
                    addMap.put("vehicleBathRoom",propertyBathRoom);
                    addMap.put("vehicleArea",propertyArea);
                    addMap.put("vehiclePrice",propertyPrice);
                    addMap.put("district",district);
                    addMap.put("shortAddress",shortAddress);

                    databaseReference.child(id).setValue(addMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(VehicleAddActivity.this, propertyName+" added Successfully", Toast.LENGTH_SHORT).show();


                                edt_Purpose_name.setText("");
                                edt_Purpose_desc.setText("");
                                edt_Purpose_phone.setText("");
                                edt_Purpose_address.setText("");
                                edt_Purpose_bedroom.setText("");
                                edt_Purpose_bathroom.setText("");
                                edt_Purpose_area.setText("");
                                edt_Purpose_price.setText("");
                                et_short_address.setText("");

                                Intent intent = new Intent(VehicleAddActivity.this,AdminDashboardActivity.class);
                                startActivity(intent);
                                finish();

                            }
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(VehicleAddActivity.this, "Cancelled.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(VehicleAddActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        district = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
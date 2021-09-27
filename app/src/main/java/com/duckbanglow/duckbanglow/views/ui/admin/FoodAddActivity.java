package com.duckbanglow.duckbanglow.views.ui.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.duckbanglow.duckbanglow.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Spliterator;

public class FoodAddActivity extends AppCompatActivity  {

    private static final int PICK_IMAGE_REQUEST = 420;
    EditText edt_food_name,edt_food_price,edt_food_desc,edt_restaurant_name,edt_restaurant_phone,edt_restaurant_address;
    ImageView iv_food;
    Button btn_sub;
    TextView image_select;
    Spinner spPropertyPurpose;

    private ProgressDialog progressDialog;

    Uri foodImageUri;


    DatabaseReference databaseReference;

    String foodName = "";
    String foodPrice = "";
    String foodDetails = "";
    String restaurantName = "";
    String restaurantAddress = "";
    String restaurantPhone = "";
    String foodImgUrl = "";
    String id = "";
    String district = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_add);
        edt_food_name = findViewById(R.id.edt_food_name);
        edt_food_price = findViewById(R.id.edt_food_price);
        edt_food_desc = findViewById(R.id.edt_food_desc);
        edt_restaurant_name = findViewById(R.id.edt_restaurant_name);
        edt_restaurant_phone = findViewById(R.id.edt_restaurant_phone);
        edt_restaurant_address = findViewById(R.id.edt_Purpose_address);
        image_select = findViewById(R.id.image_select);
        spPropertyPurpose = findViewById(R.id.spPropertyPurpose);

        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bd_district));
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPropertyPurpose.setAdapter(spinnerArrayAdapter);

        iv_food = findViewById(R.id.iv_food);
        btn_sub = findViewById(R.id.btn_sub);

        spPropertyPurpose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String dis = adapterView.getItemAtPosition(i).toString();
                Log.d("district",dis);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                foodName = edt_food_name.getText().toString().trim();
                foodPrice = edt_food_price.getText().toString().trim();
                foodDetails = edt_food_desc.getText().toString().trim();
                restaurantName = edt_restaurant_name.getText().toString().trim();
                restaurantAddress = edt_restaurant_address.getText().toString().trim();
                restaurantPhone = edt_restaurant_phone.getText().toString().trim();
                id =  databaseReference.push().getKey().toString();
                HashMap<String,String> foodMap = new HashMap<>();
                if (foodName.length()>5 && foodPrice.length() > 1 && foodDetails.length()>5 && restaurantName.length()>5 && restaurantAddress.length()>5 && restaurantPhone.length()>5){
                    foodMap.put("id",id);
                    foodMap.put("foodName",foodName);
                    foodMap.put("foodPrice",foodPrice);
                    foodMap.put("foodDetails",foodDetails);
                    foodMap.put("restaurantName",restaurantName);
                    foodMap.put("restaurantAddress",restaurantAddress);
                    foodMap.put("district",district);
                    foodMap.put("restaurantPhone",restaurantPhone);
                    foodMap.put("foodImgUrl",foodImgUrl);

                    databaseReference.child(id).setValue(foodMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(FoodAddActivity.this, foodName +" Added", Toast.LENGTH_SHORT).show();

                                foodName = edt_food_name.getText().toString().trim();
                                foodPrice = edt_food_price.getText().toString().trim();
                                foodDetails = edt_food_desc.getText().toString().trim();
                                restaurantName = edt_restaurant_name.getText().toString().trim();
                                restaurantAddress = edt_restaurant_address.getText().toString().trim();
                                restaurantPhone = edt_restaurant_phone.getText().toString().trim();

                                edt_food_name.setText("");
                                edt_food_price.setText("");
                                edt_food_desc.setText("");
                                edt_restaurant_name.setText("");
                                edt_restaurant_address.setText("");
                                edt_restaurant_phone.setText("");

                                Intent intent = new Intent(FoodAddActivity.this,AdminDashboardActivity.class);
                                startActivity(intent);
                                finish();


                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(FoodAddActivity.this, "Error "+e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                        @Override
                        public void onCanceled() {
                            Toast.makeText(FoodAddActivity.this, foodName+" can't Added", Toast.LENGTH_SHORT).show();
                        }
                    });


                }
            }
        });


        image_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chooseImage();
            }
        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            foodImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), foodImageUri);
                iv_food.setImageBitmap(bitmap);

                if (foodImageUri != null)
                    foodImageUpload(foodImageUri);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    private void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void foodImageUpload(Uri imgUri){

        //final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Property");


            if (imgUri != null) {
                progressDialog = new ProgressDialog(FoodAddActivity.this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                //final StorageReference ref = storageReference.child(user_uid+".jpg");
                final StorageReference ref = FirebaseStorage.getInstance().getReference().child("food_image").child("food_image"+databaseReference.push() + ".jpg");
                ref.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        progressDialog.dismiss();
                        Toast.makeText(FoodAddActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                foodImgUrl = uri.toString();
                                //databaseReference.child(pId).child("gallery_image").setValue(uri.toString());
                            }
                        });
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(FoodAddActivity.this, "Failed " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                                progressDialog.setMessage("Uploading " + (int) progress + "%");
                            }
                        });
            }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;

            default:
                return super.onOptionsItemSelected(menuItem);
        }
        return true;
    }

 /*   @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        district = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(FoodAddActivity.this, ""+district, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }*/
}
package com.duckbanglowofficial.duckbanglow.views.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.duckbanglowofficial.duckbanglow.R;
import com.duckbanglowofficial.duckbanglow.services.models.Division;
import com.duckbanglowofficial.duckbanglow.services.models.DivisionData;
import com.duckbanglowofficial.duckbanglow.viewmodels.DivisionViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout ll_profile_start,ll_requestservice_start,ll_contactus_start,ll_howtouse_start;

    private DivisionViewModel divisionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        divisionViewModel = new ViewModelProvider(this).get(DivisionViewModel.class);


        initViews();
        initListeners();

       /* divisionViewModel.getTotalDivisionList().observe(this, new Observer<List<DivisionData>>() {
            @Override
            public void onChanged(List<DivisionData> divisions) {
                if(divisions != null){
                    // divisions data found.
                }else{
                    // division data not found
                }
            }
        });*/
    }

    private void initViews() {
        ll_profile_start=findViewById(R.id.ll_profile_start);
        ll_requestservice_start=findViewById(R.id.ll_requestservice_start);
        ll_contactus_start=findViewById(R.id.ll_contactus_start);
        ll_howtouse_start=findViewById(R.id.ll_howtouse_start);
    }

    private void initListeners() {
        ll_profile_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Profile.class));
                finish();
            }
        });

        ll_contactus_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String to="contact@gmail.com";
                String subject= getString(R.string.app_name);
                String message="";


                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                //need this to prompts email client only
                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Choose an Email client :"));
            }
        });

        ll_howtouse_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://www.whatsapp.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "Please Wait...", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
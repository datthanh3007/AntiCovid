package com.example.anticovid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.anticovid.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SeeDetailCountryActivity extends AppCompatActivity {
    TextView txtNewInfected,txtTotalInfected,txtNewDeath,txtTotalDeath,txtRecovered,txtTotalRecovered,txtCountriesName,txtDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_detail_country);
        init();
        Intent intent=getIntent();
        Bundle bundle = intent.getBundleExtra("data");
        String NameCountry  = bundle.getString("NameCountry");
        int  NewInfected  = bundle.getInt("NewInfected");
        int TotalInfected= bundle.getInt("TotalInfected");
        int NewDeath  = bundle.getInt("NewDeath");
        int TotalDeath  = bundle.getInt("TotalDeath");
        int Recovered  = bundle.getInt("Recovered");
        int TotalRecovered  = bundle.getInt("TotalRecovered");
        String Date = bundle.getString("Date");
        txtCountriesName.setText(NameCountry);
        txtNewInfected.setText("+"+NewInfected+"");
        txtTotalInfected.setText(TotalInfected+"");
        txtNewDeath.setText("+"+NewDeath+"");
        txtTotalDeath.setText(TotalDeath+"");
        txtRecovered.setText("+"+Recovered+"");
        txtTotalRecovered.setText(TotalRecovered+"");
        SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'");
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = null;
        try {
            date = inputFormat.parse(Date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = outputFormat.format(date);

        txtDate.setText(formattedDate);
    }
    private void init() {
        txtCountriesName=findViewById(R.id.txtCountriesName);
        txtNewInfected=findViewById(R.id.txtNewInfected);
        txtTotalInfected=findViewById(R.id.txtTotalInfected);
        txtNewDeath=findViewById(R.id.txtNewDeath);
        txtTotalDeath =findViewById(R.id.txtTotalDeath);
        txtRecovered =findViewById(R.id.txtNewRecovered);
        txtTotalRecovered=findViewById(R.id.txtTotalRecovered);
        txtDate = findViewById(R.id.textViewUpdateTime);
    }
}
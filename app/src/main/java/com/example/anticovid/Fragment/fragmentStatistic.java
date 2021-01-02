package com.example.anticovid.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.example.anticovid.Activity.MainActivitySeeDetail;
import com.example.anticovid.Data.LoadingDialog;
import com.example.anticovid.R;
import com.example.anticovid.util.AsynctaskCallBack;
import com.example.anticovid.Data.MyAsynctask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class fragmentStatistic extends Fragment {
    private static final String LINK_API = "https://api.covid19api.com/summary";

    View view;
    TextView txtSeeDetail;
    RadioGroup radioGroup;
    RadioButton rdbVietNam,rbdWorld;
    TextView txtTotalInfected,txtInfected,txtTotalDeath,txtDeath,txtRecovered,txtTotalRecovered,txtUpdateTime;

    int InfectedGlobal=0;
    int TotalInfectedGlobal=0;
    int DeathGlobal=0;
    int TotalDeathGlobal=0;
    int RecoveredGlobal=0;
    int TotalRecoveredGlobal=0;
    String Day ="";

    int Infected=0;
    int TotalInfected=0 ;
    int Death=0 ;
    int TotalDeath=0;
    int Recovered=0 ;
    int TotalRecovered=0 ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      view = inflater.inflate(R.layout.fragment_statistic,container,false);
      init();
      readAPI();
        final LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        rdbVietNam.setChecked(true);
        rdbVietNam.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    rbdWorld.setChecked(false);
                    txtInfected.setText(getString(R.string.text_plus_information,Infected));
                    loadingDialog.startLoadingDialog();
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    },400);
                    // txtInfected.setText("+"+Infected);
                    // txtTotalInfected.setText(TotalInfected+"");
                    txtTotalInfected.setText(getString(R.string.text_information,TotalInfected));
                    txtDeath.setText("+"+Death);
                    txtTotalDeath.setText(TotalDeath+"");
                    txtRecovered.setText("+"+Recovered);
                    txtTotalRecovered.setText(TotalRecovered+"");

                    SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:SS'Z'");
                    SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Date date = null;
                    try {
                        date = inputFormat.parse(Day);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    String formattedDate = outputFormat.format(date);
                    // System.out.println(formattedDate); // prints 10-04-2018
                    txtUpdateTime.setText(formattedDate);

                }
            }
        });
        rbdWorld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    rdbVietNam.setChecked(false);
                    loadingDialog.startLoadingDialog();
                    Handler handler =new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loadingDialog.dismissDialog();
                        }
                    },400);
                    txtTotalInfected.setText(TotalInfectedGlobal+"");
                    //   txtInfected.setText("+"+InfectedGlobal);
                    txtInfected.setText(getString(R.string.text_plus_information,InfectedGlobal));
                    txtDeath.setText("+"+DeathGlobal);
                    txtTotalDeath.setText(TotalDeathGlobal+"");
                    txtRecovered.setText("+"+RecoveredGlobal);
                    txtTotalRecovered.setText(TotalRecoveredGlobal+"");


                }
            }
        });
      return  view;



    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSeeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainActivitySeeDetail.class);
                startActivity(intent);
            }
        });
    }
    private void setDataDefault() {
            txtInfected.setText("+" + Infected);
            txtTotalInfected.setText(TotalInfected+"");
            txtDeath.setText("+" + Death+"");
            txtTotalDeath.setText(TotalDeath+"");
            txtRecovered.setText("+" + Recovered+"");
            txtTotalRecovered.setText(TotalRecovered+"");
    }
    private void readAPI(){

        new MyAsynctask(new AsynctaskCallBack() {
            @Override
            public void onSuccess(String data) {
                getAPIContent(data);
            }

            @Override
            public void onError(String error) {
                Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();
            }
        }).execute(LINK_API);
    }
    private void getAPIContent(String data){
        try {
            JSONObject jsonObject = new JSONObject(data);
             Day =jsonObject.getString("Date");
             JSONObject jsonObjectGlobal =jsonObject.getJSONObject("Global");
             InfectedGlobal=jsonObjectGlobal.getInt("NewConfirmed");
             TotalInfectedGlobal=jsonObjectGlobal.getInt("TotalConfirmed");
             DeathGlobal=jsonObjectGlobal.getInt("NewDeaths");
             TotalDeathGlobal=jsonObjectGlobal.getInt("TotalDeaths");
             RecoveredGlobal=jsonObjectGlobal.getInt("NewRecovered");
             TotalRecoveredGlobal=jsonObjectGlobal.getInt("TotalRecovered");

            JSONArray jsonArrayCountry = jsonObject.getJSONArray("Countries");
            JSONObject jsonObjectCountryChild;
            for(int i= 0;i< jsonArrayCountry.length();i++){
               if( jsonArrayCountry.getJSONObject(i).get("Country").equals("Viet Nam")){
                   jsonObjectCountryChild=jsonArrayCountry.getJSONObject(i);
                    Infected=jsonObjectCountryChild.getInt("NewConfirmed");
                    TotalInfected = jsonObjectCountryChild.getInt("TotalConfirmed");
                    Death = jsonObjectCountryChild.getInt("NewDeaths");
                    TotalDeath = jsonObjectCountryChild.getInt("TotalDeaths");
                    Recovered = jsonObjectCountryChild.getInt("NewRecovered");
                    TotalRecovered = jsonObjectCountryChild.getInt("TotalRecovered");
               }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }finally {
            setDataDefault();
        }
    }
    private void init() {
        txtUpdateTime = view.findViewById(R.id.textViewUpdateTime);
        txtSeeDetail=view.findViewById(R.id.textViewSeeDetail);
        radioGroup=view.findViewById(R.id.radioGroupToggleInformation);
        rdbVietNam = view.findViewById(R.id.radioButtonVietnamese);
        rbdWorld =view.findViewById(R.id.radioButtonWorld);
        txtTotalInfected= view.findViewById(R.id.textViewTotalInfected);
        txtInfected= view.findViewById(R.id.textViewNewInfected);
        txtDeath= view.findViewById(R.id.textViewNewDeath);
        txtTotalDeath=view.findViewById(R.id.textViewTotalDeath);
        txtRecovered = view.findViewById(R.id.textViewNewRecovered);
        txtTotalRecovered= view.findViewById(R.id.textViewTotalRecovered);

    }



}

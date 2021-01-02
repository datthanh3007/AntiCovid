package com.example.anticovid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticovid.Fragment.fragmentDialogSupport;
import com.example.anticovid.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ActivityDeclareHealth extends AppCompatActivity {
    private Button btnSupport,btnfinish,btnbackhome;
    private EditText edtNote;
    private CheckBox sot,ho,khotho,viemphoi,dauhong,metmoi,khoe;
    private CheckBox huyetapcao,benhtimmach,benhphoimantinh;
    private String status;
    private String health;
    private String urlInsert = "https://anticovid19datn.000webhostapp.com/Server/InsertHealth.php";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declare_health);
        init();
        sharedPreferences = getSharedPreferences("datalocal",MODE_PRIVATE);
        fragmentDialogSupport fragmentDialogSupport = new fragmentDialogSupport();

        khoe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if( b){
                    sot.setChecked(false);
                    ho.setChecked(false);
                    khotho.setChecked(false);
                    viemphoi.setChecked(false);
                    dauhong.setChecked(false);
                    metmoi.setChecked(false);
                }
            }
        });
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status ="";
                health="";
                if(sot.isChecked()){
                    status = status +  sot.getText().toString().trim()+",";
                }
                if(viemphoi.isChecked()){
                    status = status +  viemphoi.getText().toString().trim()+",";
                }
                if (ho.isChecked()){
                    status = status +  ho.getText().toString().trim()+",";
                }
                if(dauhong.isChecked()){
                    status = status +  dauhong.getText().toString().trim()+",";
                }
                if(khotho.isChecked()){
                    status = status +  khotho.getText().toString().trim()+",";
                }
                if(metmoi.isChecked()){
                    status = status +  metmoi.getText().toString().trim()+",";
                }

                if(huyetapcao.isChecked()){
                    health=health+huyetapcao.getText().toString().trim();
                }
                if(benhtimmach.isChecked()){
                    health=health+benhtimmach.getText().toString().trim();
                }
                if(benhphoimantinh.isChecked()){
                    health=health+benhphoimantinh.getText().toString().trim();
                }

                if(sot.isChecked()==false&&viemphoi.isChecked()==false&&ho.isChecked()==false&&dauhong.isChecked()==false&&khotho.isChecked()==false&&
                        metmoi.isChecked()==false&&benhphoimantinh.isChecked()==false&&benhtimmach.isChecked()==false&&huyetapcao.isChecked()==false&&khoe.isChecked()==false){
                    Toast.makeText(ActivityDeclareHealth.this,"Khai báo không thành công",Toast.LENGTH_LONG).show();
                }
                else{
                    insertData(urlInsert);
                    sot.setChecked(false);
                    viemphoi.setChecked(false);
                    ho.setChecked(false);
                    dauhong.setChecked(false);
                    khotho.setChecked(false);
                    metmoi.setChecked(false);
                    benhtimmach.setChecked(false);
                    benhtimmach.setChecked(false);
                    huyetapcao.setChecked(false);
                    khoe.setChecked(false);
                    edtNote.setText("");
                    Toast.makeText(ActivityDeclareHealth.this,"Khai báo thành công",Toast.LENGTH_LONG).show();
                }
            }
        });
        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btnSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentDialogSupport.show(getSupportFragmentManager(),"dialogSupport");
            }
        });
    }

    private void init() {
        edtNote = findViewById(R.id.edtkhaibao);
        btnbackhome=findViewById(R.id.btnbackhome);
        btnfinish = findViewById(R.id.btnsent);
        btnSupport = findViewById(R.id.btnSupport);
        sot = findViewById(R.id.cbsot);
        ho = findViewById(R.id.cbho);
        khotho = findViewById(R.id.cbkhotho);
        viemphoi = findViewById(R.id.cbvienphoi);
        dauhong = findViewById(R.id.cbdauhong);
        metmoi = findViewById(R.id.cbmetmoi);
        khoe = findViewById(R.id.cbkhoe);
        huyetapcao = findViewById(R.id.cbhuyetapcao);
        benhtimmach = findViewById(R.id.cbtimmach);
        benhphoimantinh = findViewById(R.id.cbphoimantinh);
    }
    private void insertData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                          //  Toast.makeText(ActivityDeclareHealth.this, "Success", Toast.LENGTH_LONG).show();
                        } else {
                           // Toast.makeText(ActivityDeclareHealth.this, "FaileD", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(ActivityDeclareHealth.this, "FaileT", Toast.LENGTH_LONG).show();
                Log.d("BBB", "ERR" + error.toString());
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("CCCD",sharedPreferences.getString("cccd",""));
                param.put("Note",edtNote.getText().toString().trim());
                param.put("Status", status);
                param.put("Health",health);
                param.put("Date",getDateTime());
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
package com.example.anticovid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticovid.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ActivityDeclaretion extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    private EditText nameDeclare, cccdDeclare, addressDeclare, phonenumberDeclare, dateDeclare;
    private RadioButton rbnmale, rbnfemale;
    private Button btnSubmit,btnCancel;
    private TextView txtdeclare;
    DatePickerDialog.OnDateSetListener setListener;
    private String name = "";
    private String cccd = "";
    private String address = "";
    private String phonenumber = "";
    private String gender = "";
    String urlInsert = "https://anticovid19datn.000webhostapp.com/Server/InserDeclare.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaretion);
        init();
        sharedPreferences =getSharedPreferences("datalocal",MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month  =calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        dateDeclare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog  = new DatePickerDialog(
                        ActivityDeclaretion.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                month = month+1;
                                String date = year+"-"+month+"-"+day;
                                dateDeclare.setText(date);
                            }
                        },year,month,day);
                datePickerDialog.show();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cccd = cccdDeclare.getText().toString().trim();
                address = addressDeclare.getText().toString().trim();
                phonenumber = phonenumberDeclare.getText().toString().trim();
                name = nameDeclare.getText().toString().trim();

                if (rbnmale.isChecked()) {
                    gender = rbnmale.getText().toString().trim();
                } else {
                    gender = rbnfemale.getText().toString().trim();
                }
            //    sharedPreferences.edit().remove("cccd").commit();
                Log.d("acc","Cache"+sharedPreferences.getString("cccd",""));


                    if (cccd.isEmpty() || address.isEmpty() || phonenumber.isEmpty() || name.isEmpty()) {
                        Toast.makeText(ActivityDeclaretion.this, "Khai báo không thành công", Toast.LENGTH_LONG).show();

                    } else {
                        insertData(urlInsert);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("cccd",cccd);
                       editor.putString("phonenumber",phonenumber);
                       editor.commit();

                        Toast.makeText(ActivityDeclaretion.this, "Khai báo thành công ", Toast.LENGTH_LONG).show();
                    }


            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void insertData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                           // Toast.makeText(ActivityDeclaretion.this, "Success", Toast.LENGTH_LONG).show();

                        } else {
                  //          Toast.makeText(ActivityDeclaretion.this, "Faile", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(ActivityDeclaretion.this, "Faile", Toast.LENGTH_LONG).show();
                Log.d("AAA", "ERR" + error.toString());

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("CCCDuser", cccdDeclare.getText().toString().trim());
                param.put("DateofBirth", dateDeclare.getText().toString().trim());
                param.put("Address", addressDeclare.getText().toString().trim());
                param.put("PhoneNumber", phonenumberDeclare.getText().toString().trim());
                param.put("NameUser", nameDeclare.getText().toString().trim());
                param.put("Gender", gender);
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void init() {
        nameDeclare = findViewById(R.id.edtnameDecalre);
        cccdDeclare = findViewById(R.id.edtcccdDeclare);
        addressDeclare = findViewById(R.id.edtaddressDeclare);
        phonenumberDeclare = findViewById(R.id.edtphonenumberDeclare);
        rbnmale = findViewById(R.id.rbnmale);
        rbnfemale = findViewById(R.id.rbnfemale);
        dateDeclare = findViewById(R.id.edtdate);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);
    }
}
package com.example.anticovid.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.anticovid.Activity.ActivityDeclareHealth;
import com.example.anticovid.Activity.ActivityDeclaretion;
import com.example.anticovid.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class fragmentDialogSupport extends DialogFragment {
    SharedPreferences sharedPreferences;
    private View view;
    private CheckBox cb1,cb2,cb3;
    private Button btnfinish,btnback1;
    private String status;
    private String urlInsert = "https://anticovid19datn.000webhostapp.com/Server/InsertSupport.php";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragmentdialogsupport,container,false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cb1= view.findViewById(R.id.cb1);
        cb2 =view.findViewById(R.id.cb2);
        cb3= view.findViewById(R.id.cb3);
        btnback1=view.findViewById(R.id.btnback1);
        btnfinish =view.findViewById(R.id.btnback);
        sharedPreferences =getContext().getSharedPreferences("datalocal", Context.MODE_PRIVATE);
        btnback1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status="";
                if (cb1.isChecked()){
                    status=status+ cb1.getText().toString().trim()+", ";
                }
                if (cb2.isChecked()){
                    status=status+ cb2.getText().toString().trim()+", ";
                }
                if (cb3.isChecked()){
                    status=status+ cb3.getText().toString().trim()+", ";
                }
                if(cb1.isChecked()==false&&cb2.isChecked()==false&&cb3.isChecked()==false){
                    Toast.makeText(getContext(),"Bạn hãy chọn đủ",Toast.LENGTH_LONG).show();
                }
                else{
                    insertData(urlInsert);
                  cb1.setChecked(false);
                  cb2.setChecked(false);
                  cb3.setChecked(false);
                  Toast.makeText(getContext(),"Hoàn thành",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void insertData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                        //    Toast.makeText(getActivity(), "Success", Toast.LENGTH_LONG).show();

                        } else {
                        //    Toast.makeText(getActivity(), "Faile", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              //  Toast.makeText(getActivity(), "Faile", Toast.LENGTH_LONG).show();
                Log.d("AAA", "ERR" + error.toString());

            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
               // param.put("PhoneNumber", phonenumberDeclare.getText().toString().trim());
                param.put("Status",status);
                param.put("CCCD",sharedPreferences.getString("cccd","") );
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

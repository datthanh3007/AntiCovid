package com.example.anticovid.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anticovid.Adapter.SeedetailCountriesAdapter;
import com.example.anticovid.Model.Countries;
import com.example.anticovid.R;
import com.example.anticovid.util.AsynctaskCallBack;
import com.example.anticovid.Data.MyAsynctask;
import com.example.anticovid.util.OnItemClickListenerCountries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivitySeeDetail extends AppCompatActivity {
    private static final String LINK_API = "https://api.covid19api.com/summary";
    private RecyclerView recyclerView;
    private EditText edtSearch;
    private SeedetailCountriesAdapter seedetailCountriesAdapter;
    private ArrayList<Countries> arrListCountry=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_see_detail);
        edtSearch = findViewById(R.id.editTextSearch);
        recyclerView=findViewById(R.id.recyclerViewCountries);
        ReadCountry();
        seedetailCountriesAdapter= new SeedetailCountriesAdapter(onItemClickListenerCountries,arrListCountry);
        recyclerView.setAdapter(seedetailCountriesAdapter);
        //search
        // SearchView searchView =(SearchView) btnSearch.get;

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });

    }

    private void filter(String text) {
        ArrayList<Countries> filterList = new ArrayList<>();
        for (Countries item: arrListCountry ){
            if(item.getCountry().toLowerCase().contains(text.toLowerCase())){
                filterList.add(item);
            }
        }
        seedetailCountriesAdapter.filteredList(filterList);
    }

    private void ReadCountry(){
       new MyAsynctask(new AsynctaskCallBack() {
           @Override
           public void onSuccess(String data) {
               getAPIContent(data);
           }

           @Override
           public void onError(String error) {
               Toast.makeText(MainActivitySeeDetail.this,error,Toast.LENGTH_LONG).show();
           }
       }).execute(LINK_API);
    }
    private void getAPIContent(String data){
        try {
            JSONObject jsonObject= new JSONObject(data);
            JSONArray jsonArrayCountry = jsonObject.getJSONArray("Countries");
            for (int i=0;i< jsonArrayCountry.length();i++){
                JSONObject jsonObjectCountryChild = jsonArrayCountry.getJSONObject(i);
                String Countryname =jsonObjectCountryChild.getString("Country");
                String Countrycode =jsonObjectCountryChild.getString("CountryCode");
                String Slug = jsonObjectCountryChild.getString("Slug");
                int Infected=jsonObjectCountryChild.getInt("NewConfirmed");
                int TotalInfected = jsonObjectCountryChild.getInt("TotalConfirmed");
                int Death = jsonObjectCountryChild.getInt("NewDeaths");
                int TotalDeath = jsonObjectCountryChild.getInt("TotalDeaths");
                int Recovered = jsonObjectCountryChild.getInt("NewRecovered");
                int TotalRecovered = jsonObjectCountryChild.getInt("TotalRecovered");
                String Date= jsonObjectCountryChild.getString("Date");
                String Premium = jsonObjectCountryChild.getString("Premium");
                arrListCountry.add(new Countries(Countryname,Countrycode,Slug,Infected,TotalInfected,Death,TotalDeath,Recovered,TotalRecovered,Date,Premium));
            }
            seedetailCountriesAdapter.updateData(arrListCountry);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public OnItemClickListenerCountries onItemClickListenerCountries = new OnItemClickListenerCountries() {
        @Override
        public void OnitemClickCountries(Countries countries) {
            Intent intent = new Intent(MainActivitySeeDetail.this,SeeDetailCountryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("NameCountry",countries.getCountry());
            bundle.putInt("NewInfected",countries.getNewConfirmed());
            bundle.putInt("TotalInfected",countries.getTotalConfirmed());
            bundle.putInt("NewDeath",countries.getNewDeaths());
            bundle.putInt("TotalDeath",countries.getTotalDeaths());
            bundle.putInt("Recovered",countries.getNewRecovered());
            bundle.putInt("TotalRecovered",countries.getTotalRecovered());
            bundle.putString("Date",countries.getDate());
            intent.putExtra("data",bundle);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        }
    };


}
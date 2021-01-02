package com.example.anticovid.Service.ApiInfected;

import com.example.anticovid.Model.Infected;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {

    @GET("getdataInfected.php")
    Call<List<Infected>> getdataInfected();
}

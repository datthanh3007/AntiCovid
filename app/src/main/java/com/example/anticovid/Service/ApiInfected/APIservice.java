package com.example.anticovid.Service.ApiInfected;

public class APIservice {
     private static  String base_url ="https://anticovid19datn.000webhostapp.com/Server/";
     public static Dataservice getService(){
         return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
     }
}

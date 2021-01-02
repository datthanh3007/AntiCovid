package com.example.anticovid.Model;

public class Countries {

    private  String country;
    private  String countryCode;
    private  String slug;
    private  int newConfirmed;
    private  int totalConfirmed;
    private  int newDeaths;
    private  int totalDeaths;
    private  int newRecovered;
    private  int totalRecovered;
    private  String date;
    private  String premium;

    public Countries(String country, String countryCode, String slug, int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered, String date, String premium) {
        this.country = country;
        this.countryCode = countryCode;
        this.slug = slug;
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
        this.date = date;
        this.premium = premium;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public  String getCountry() {
        return country;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public  String getSlug() {
        return slug;
    }

    public  int getNewConfirmed() {
        return newConfirmed;
    }

    public  int getTotalConfirmed() {
        return totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public  int getTotalDeaths() {
        return totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public String getDate() {
        return date;
    }

    public String getPremium() {
        return premium;
    }


}

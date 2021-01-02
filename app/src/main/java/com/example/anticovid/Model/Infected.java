package com.example.anticovid.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Infected {

@SerializedName("idInfected")
@Expose
private String idInfected;
@SerializedName("NameInfected")
@Expose
private String nameInfected;
@SerializedName("Address")
@Expose
private String address;
@SerializedName("Time")
@Expose
private String time;
@SerializedName("Note")
@Expose
private String note;
@SerializedName("Longitude")
@Expose
private String longitude;
@SerializedName("Latitude")
@Expose
private String latitude;

public String getIdInfected() {
return idInfected;
}

public void setIdInfected(String idInfected) {
this.idInfected = idInfected;
}

public String getNameInfected() {
return nameInfected;
}

public void setNameInfected(String nameInfected) {
this.nameInfected = nameInfected;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getTime() {
return time;
}

public void setTime(String time) {
this.time = time;
}

public String getNote() {
return note;
}

public void setNote(String note) {
this.note = note;
}

public String getLongitude() {
return longitude;
}

public void setLongitude(String longitude) {
this.longitude = longitude;
}

public String getLatitude() {
return latitude;
}

public void setLatitude(String latitude) {
this.latitude = latitude;
}

}
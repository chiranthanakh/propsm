package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saveconsuptionstoredetails {

    @SerializedName("contractor_id")
    @Expose
    private String contractor_id;

    @SerializedName("location_id")
    @Expose
    private String location_id;

    @SerializedName("sub_location_id")
    @Expose
    private String sub_location_id;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    public Saveconsuptionstoredetails(String contractor_id, String location_id, String sub_location_id, String date, String user_id) {
        this.contractor_id = contractor_id;
        this.location_id = location_id;
        this.sub_location_id = sub_location_id;
        this.date = date;
        this.user_id = user_id;
    }

    public String getContractor_id() {
        return contractor_id;
    }

    public void setContractor_id(String contractor_id) {
        this.contractor_id = contractor_id;
    }

    public String getLocation_id() {
        return location_id;
    }

    public void setLocation_id(String location_id) {
        this.location_id = location_id;
    }

    public String getSub_location_id() {
        return sub_location_id;
    }

    public void setSub_location_id(String sub_location_id) {
        this.sub_location_id = sub_location_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}

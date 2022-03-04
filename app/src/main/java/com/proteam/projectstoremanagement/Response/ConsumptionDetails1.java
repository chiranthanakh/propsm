package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumptionDetails1 {

    @SerializedName("site_consumption_id")
    @Expose
    private String site_consumption_id;

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

    @SerializedName("last_updated_date")
    @Expose
    private String last_updated_date;

    @SerializedName("contractor_name")
    @Expose
    private String contractor_name;

    @SerializedName("location_name")
    @Expose
    private String location_name;

    @SerializedName("sub_location_name")
    @Expose
    private String sub_location_name;

    public String getSite_consumption_id() {
        return site_consumption_id;
    }

    public void setSite_consumption_id(String site_consumption_id) {
        this.site_consumption_id = site_consumption_id;
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

    public String getLast_updated_date() {
        return last_updated_date;
    }

    public void setLast_updated_date(String last_updated_date) {
        this.last_updated_date = last_updated_date;
    }

    public String getContractor_name() {
        return contractor_name;
    }

    public void setContractor_name(String contractor_name) {
        this.contractor_name = contractor_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getSub_location_name() {
        return sub_location_name;
    }

    public void setSub_location_name(String sub_location_name) {
        this.sub_location_name = sub_location_name;
    }
}

package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumptionListDataResponse {
    @SerializedName("contractor_name")
    @Expose
    private String contractor_name;

    @SerializedName("location_name")
    @Expose
    private String location_name;

    @SerializedName("date")
    @Expose
    private String date;


    @SerializedName("site_consumption_id")
    @Expose
    private String site_consumption_id;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSite_consumption_id() {
        return site_consumption_id;
    }

    public void setSite_consumption_id(String site_consumption_id) {
        this.site_consumption_id = site_consumption_id;
    }
}

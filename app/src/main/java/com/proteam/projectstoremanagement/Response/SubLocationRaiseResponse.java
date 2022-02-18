package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubLocationRaiseResponse {

    @SerializedName("location_id")
    @Expose
    private int location_id;

    @SerializedName("location_manual_id")
    @Expose
    private String location_manual_id;

    @SerializedName("location_name")
    @Expose
    private String location_name;

    public int getLocation_id() {
        return location_id;
    }

    public void setLocation_id(int location_id) {
        this.location_id = location_id;
    }

    public String getLocation_manual_id() {
        return location_manual_id;
    }

    public void setLocation_manual_id(String location_manual_id) {
        this.location_manual_id = location_manual_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }
}

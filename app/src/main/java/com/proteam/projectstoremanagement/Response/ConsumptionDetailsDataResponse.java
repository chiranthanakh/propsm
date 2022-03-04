package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConsumptionDetailsDataResponse {

    @SerializedName("contractor_location")
    @Expose
    private List<ConsumptionDetails1> contractor_location = null;


    @SerializedName("materils")
    @Expose
    private List<ConsumptionDetails2> materils = null;

    public List<ConsumptionDetails1> getContractor_location() {
        return contractor_location;
    }

    public void setContractor_location(List<ConsumptionDetails1> contractor_location) {
        this.contractor_location = contractor_location;
    }

    public List<ConsumptionDetails2> getMaterils() {
        return materils;
    }

    public void setMaterils(List<ConsumptionDetails2> materils) {
        this.materils = materils;
    }
}

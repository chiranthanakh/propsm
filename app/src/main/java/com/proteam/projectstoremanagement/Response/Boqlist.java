package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Boqlist {

    @SerializedName("boq_list")
    @Expose
    private List<Boqitems> boq_list = null;

    public List<Boqitems> getBoq_list() {
        return boq_list;
    }

    public void setBoq_list(List<Boqitems> boq_list) {
        this.boq_list = boq_list;
    }
}

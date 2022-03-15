package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Individual_updateList {


    @SerializedName("material_details")
    @Expose
    private ArrayList<Individual_updateitems> material_details = null;


    public Individual_updateList(ArrayList<Individual_updateitems> material_details) {
        this.material_details = material_details;
    }

    public ArrayList<Individual_updateitems> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(ArrayList<Individual_updateitems> material_details) {
        this.material_details = material_details;
    }
}

package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SaveConsumptionLists {


    @SerializedName("contractor_store_details")
    @Expose
    private ArrayList<Saveconsuptionstoredetails> contractor_store_details = null;

    @SerializedName("material_details")
    @Expose
    private ArrayList<Saveconsuptionmaterialdetails> material_details = null;

    public SaveConsumptionLists(ArrayList<Saveconsuptionstoredetails> contractor_store_details, ArrayList<Saveconsuptionmaterialdetails> material_details) {
        this.contractor_store_details = contractor_store_details;
        this.material_details = material_details;
    }

    public ArrayList<Saveconsuptionstoredetails> getContractor_store_details() {
        return contractor_store_details;
    }

    public void setContractor_store_details(ArrayList<Saveconsuptionstoredetails> contractor_store_details) {
        this.contractor_store_details = contractor_store_details;
    }

    public ArrayList<Saveconsuptionmaterialdetails> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(ArrayList<Saveconsuptionmaterialdetails> material_details) {
        this.material_details = material_details;
    }
}

package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IndividualListrequest {

    @SerializedName("individual_details")
    @Expose
    private ArrayList<Individuallistitems> individual_details = null;

    @SerializedName("material_details")
    @Expose
    private ArrayList<Individualmaterialdetails> material_details = null;

    public IndividualListrequest(ArrayList<Individuallistitems> individual_details, ArrayList<Individualmaterialdetails> material_details) {
        this.individual_details = individual_details;
        this.material_details = material_details;
    }

    public ArrayList<Individuallistitems> getIndividual_details() {
        return individual_details;
    }

    public void setIndividual_details(ArrayList<Individuallistitems> individual_details) {
        this.individual_details = individual_details;
    }

    public ArrayList<Individualmaterialdetails> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(ArrayList<Individualmaterialdetails> material_details) {
        this.material_details = material_details;
    }
}

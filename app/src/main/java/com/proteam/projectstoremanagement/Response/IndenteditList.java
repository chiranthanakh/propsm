package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndenteditList {


    @SerializedName("material_details")
    @Expose
    private List<IndentEdititems> material_details = null;

    public List<IndentEdititems> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(List<IndentEdititems> material_details) {
        this.material_details = material_details;
    }
}

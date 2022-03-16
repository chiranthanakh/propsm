package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Individualintentlistitems {

    @SerializedName("material_details")
    @Expose
    private List<Individualintentlistresponse> material_details = null;

    public List<Individualintentlistresponse> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(List<Individualintentlistresponse> material_details) {
        this.material_details = material_details;
    }
}

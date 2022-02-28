package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreviewResponsce {


    @SerializedName("status")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("material_details")
    @Expose
    private List<Previewmaterialdetails> material_details = null;

    public List<Previewmaterialdetails> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(List<Previewmaterialdetails> material_details) {
        this.material_details = material_details;
    }
}

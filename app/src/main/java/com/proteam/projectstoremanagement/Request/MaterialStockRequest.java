package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proteam.projectstoremanagement.Response.Indentstatusitems;
import com.proteam.projectstoremanagement.Response.MaterialStockResponse;

import java.util.List;

public class MaterialStockRequest {

    @SerializedName("material_closing_details")
    @Expose
    private List<MaterialStockResponse> material_closing_details = null;

    public List<MaterialStockResponse> getMaterial_closing_details() {
        return material_closing_details;
    }

    public void setMaterial_closing_details(List<MaterialStockResponse> material_closing_details) {
        this.material_closing_details = material_closing_details;
    }
}

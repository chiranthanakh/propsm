package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StockMaterialNameResponse2 {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getMaterial_manual_id() {
        return material_manual_id;
    }

    public void setMaterial_manual_id(String material_manual_id) {
        this.material_manual_id = material_manual_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }
}

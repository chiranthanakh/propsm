package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Saveconsuptionmaterialdetails {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("new_consumption_qty")
    @Expose
    private String new_consumption_qty;

    public Saveconsuptionmaterialdetails(String material_id, String new_consumption_qty) {
        this.material_id = material_id;
        this.new_consumption_qty = new_consumption_qty;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getNew_consumption_qty() {
        return new_consumption_qty;
    }

    public void setNew_consumption_qty(String new_consumption_qty) {
        this.new_consumption_qty = new_consumption_qty;
    }
}

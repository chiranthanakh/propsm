package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumptionDetails2 {

    @SerializedName("site_consumption_mat_id")
    @Expose
    private String site_consumption_mat_id;

    @SerializedName("site_consumption_id")
    @Expose
    private String site_consumption_id;

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("consumption_qty")
    @Expose
    private String consumption_qty;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;


    public String getSite_consumption_mat_id() {
        return site_consumption_mat_id;
    }

    public void setSite_consumption_mat_id(String site_consumption_mat_id) {
        this.site_consumption_mat_id = site_consumption_mat_id;
    }

    public String getSite_consumption_id() {
        return site_consumption_id;
    }

    public void setSite_consumption_id(String site_consumption_id) {
        this.site_consumption_id = site_consumption_id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getConsumption_qty() {
        return consumption_qty;
    }

    public void setConsumption_qty(String consumption_qty) {
        this.consumption_qty = consumption_qty;
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

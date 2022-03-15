package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Boqitems {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("boq_id")
    @Expose
    private String boq_id;

    @SerializedName("qty")
    @Expose
    private String qty;

    @SerializedName("balance_boq")
    @Expose
    private String balance_boq;

    @SerializedName("boq_closing_stock")
    @Expose
    private String boq_closing_stock;

    @SerializedName("boq_issued_qty")
    @Expose
    private String boq_issued_qty;

    @SerializedName("boq_consumption_qty")
    @Expose
    private String boq_consumption_qty;

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

    public String getBoq_id() {
        return boq_id;
    }

    public void setBoq_id(String boq_id) {
        this.boq_id = boq_id;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getBalance_boq() {
        return balance_boq;
    }

    public void setBalance_boq(String balance_boq) {
        this.balance_boq = balance_boq;
    }

    public String getBoq_closing_stock() {
        return boq_closing_stock;
    }

    public void setBoq_closing_stock(String boq_closing_stock) {
        this.boq_closing_stock = boq_closing_stock;
    }

    public String getBoq_issued_qty() {
        return boq_issued_qty;
    }

    public void setBoq_issued_qty(String boq_issued_qty) {
        this.boq_issued_qty = boq_issued_qty;
    }

    public String getBoq_consumption_qty() {
        return boq_consumption_qty;
    }

    public void setBoq_consumption_qty(String boq_consumption_qty) {
        this.boq_consumption_qty = boq_consumption_qty;
    }
}

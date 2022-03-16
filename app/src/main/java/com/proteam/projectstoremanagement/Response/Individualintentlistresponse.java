package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Individualintentlistresponse {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    @SerializedName("boq_qty")
    @Expose
    private String boq_qty;

    @SerializedName("indent_id")
    @Expose
    private String indent_id;

    @SerializedName("boq_balance_qty")
    @Expose
    private String boq_balance_qty;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("closing_stock")
    @Expose
    private String closing_stock;

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getIndent_qty() {
        return indent_qty;
    }

    public void setIndent_qty(String indent_qty) {
        this.indent_qty = indent_qty;
    }

    public String getBoq_qty() {
        return boq_qty;
    }

    public void setBoq_qty(String boq_qty) {
        this.boq_qty = boq_qty;
    }

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }

    public String getBoq_balance_qty() {
        return boq_balance_qty;
    }

    public void setBoq_balance_qty(String boq_balance_qty) {
        this.boq_balance_qty = boq_balance_qty;
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

    public String getClosing_stock() {
        return closing_stock;
    }

    public void setClosing_stock(String closing_stock) {
        this.closing_stock = closing_stock;
    }
}

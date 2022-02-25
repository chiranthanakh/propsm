package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Raiseintentdataitems {


    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    @SerializedName("boq_qty")
    @Expose
    private String boq_qty;

    public Raiseintentdataitems(String material_id, String indent_qty, String boq_qty) {
        this.material_id = material_id;
        this.indent_qty = indent_qty;
        this.boq_qty = boq_qty;
    }

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
}

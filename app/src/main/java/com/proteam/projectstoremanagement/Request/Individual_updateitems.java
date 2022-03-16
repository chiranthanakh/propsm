package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Individual_updateitems {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    @SerializedName("indent_id")
    @Expose
    private String indent_id;

    public Individual_updateitems(String material_id, String remarks, String indent_qty, String indent_id) {
        this.material_id = material_id;
        this.remarks = remarks;
        this.indent_qty = indent_qty;
        this.indent_id = indent_id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIndent_qty() {
        return indent_qty;
    }

    public void setIndent_qty(String indent_qty) {
        this.indent_qty = indent_qty;
    }

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }
}

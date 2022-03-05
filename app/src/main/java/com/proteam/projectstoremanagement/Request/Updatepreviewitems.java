package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Updatepreviewitems {

    @SerializedName("indent_id")
    @Expose
    private String indent_id;


    @SerializedName("material_id")
    @Expose
    private String material_id;


    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    public Updatepreviewitems(String indent_id, String material_id, String indent_qty) {
        this.indent_id = indent_id;
        this.material_id = material_id;
        this.indent_qty = indent_qty;
    }

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
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
}

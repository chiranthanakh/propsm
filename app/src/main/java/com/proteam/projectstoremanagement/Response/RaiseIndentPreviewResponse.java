package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RaiseIndentPreviewResponse {

    @SerializedName("contractor")
    @Expose
    private String contractor;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("sub_location")
    @Expose
    private String sub_location;

    @SerializedName("work_order_number")
    @Expose
    private String work_order_number;

    @SerializedName("indent_date")
    @Expose
    private String indent_date;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    @SerializedName("boq_qty")
    @Expose
    private String boq_qty;

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSub_location() {
        return sub_location;
    }

    public void setSub_location(String sub_location) {
        this.sub_location = sub_location;
    }

    public String getWork_order_number() {
        return work_order_number;
    }

    public void setWork_order_number(String work_order_number) {
        this.work_order_number = work_order_number;
    }

    public String getIndent_date() {
        return indent_date;
    }

    public void setIndent_date(String indent_date) {
        this.indent_date = indent_date;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConsumptionMaterialListItems {

    @SerializedName("out_sum")
    @Expose
    private String out_sum;

    @SerializedName("out_inv")
    @Expose
    private String out_inv;

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("client_id")
    @Expose
    private String client_id;

    @SerializedName("location")
    @Expose
    private String location;

    @SerializedName("indent_no")
    @Expose
    private String indent_no;

    @SerializedName("iss_date")
    @Expose
    private String iss_date;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("issued_qty")
    @Expose
    private String issued_qty;

    @SerializedName("last_updated_qty")
    @Expose
    private String last_updated_qty;

    public String getOut_sum() {
        return out_sum;
    }

    public void setOut_sum(String out_sum) {
        this.out_sum = out_sum;
    }

    public String getOut_inv() {
        return out_inv;
    }

    public void setOut_inv(String out_inv) {
        this.out_inv = out_inv;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIndent_no() {
        return indent_no;
    }

    public void setIndent_no(String indent_no) {
        this.indent_no = indent_no;
    }

    public String getIss_date() {
        return iss_date;
    }

    public void setIss_date(String iss_date) {
        this.iss_date = iss_date;
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

    public String getIssued_qty() {
        return issued_qty;
    }

    public void setIssued_qty(String issued_qty) {
        this.issued_qty = issued_qty;
    }

    public String getLast_updated_qty() {
        return last_updated_qty;
    }

    public void setLast_updated_qty(String last_updated_qty) {
        this.last_updated_qty = last_updated_qty;
    }
}

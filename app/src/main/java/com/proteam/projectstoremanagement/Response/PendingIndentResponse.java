package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingIndentResponse {

    @SerializedName("indent_id")
    @Expose
    private String indent_id;

    @SerializedName("indent_auto_gen_id")
    @Expose
    private String indent_auto_gen_id;

    @SerializedName("contractor")
    @Expose
    private String contractor;

    @SerializedName("block")
    @Expose
    private String block;

    @SerializedName("locations")
    @Expose
    private String locations;

    @SerializedName("indent_date")
    @Expose
    private String indent_date;

    @SerializedName("indent_type")
    @Expose
    private String indent_type;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("indent_riser")
    @Expose
    private String indent_riser;

    @SerializedName("contractor_name")
    @Expose
    private String contractor_name;

    @SerializedName("location_name")
    @Expose
    private String location_name;

    @SerializedName("sub_location_name")
    @Expose
    private String sub_location_name;

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }

    public String getIndent_auto_gen_id() {
        return indent_auto_gen_id;
    }

    public void setIndent_auto_gen_id(String indent_auto_gen_id) {
        this.indent_auto_gen_id = indent_auto_gen_id;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getIndent_date() {
        return indent_date;
    }

    public void setIndent_date(String indent_date) {
        this.indent_date = indent_date;
    }

    public String getIndent_type() {
        return indent_type;
    }

    public void setIndent_type(String indent_type) {
        this.indent_type = indent_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getIndent_riser() {
        return indent_riser;
    }

    public void setIndent_riser(String indent_riser) {
        this.indent_riser = indent_riser;
    }

    public String getContractor_name() {
        return contractor_name;
    }

    public void setContractor_name(String contractor_name) {
        this.contractor_name = contractor_name;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getSub_location_name() {
        return sub_location_name;
    }

    public void setSub_location_name(String sub_location_name) {
        this.sub_location_name = sub_location_name;
    }
}

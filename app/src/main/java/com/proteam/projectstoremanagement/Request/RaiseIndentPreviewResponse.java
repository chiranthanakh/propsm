package com.proteam.projectstoremanagement.Request;

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

    public RaiseIndentPreviewResponse(String contractor, String location, String sub_location, String work_order_number, String indent_date, String store_id, String user_id, String remarks) {
        this.contractor = contractor;
        this.location = location;
        this.sub_location = sub_location;
        this.work_order_number = work_order_number;
        this.indent_date = indent_date;
        this.store_id = store_id;
        this.user_id = user_id;
        this.remarks = remarks;
    }

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


}

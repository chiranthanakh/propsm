package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Indentstatusitems {

    @SerializedName("indent_auto_gen_id")
    @Expose
    private String indent_auto_gen_id;

    @SerializedName("contractor_name")
    @Expose
    private String contractor_name;

    @SerializedName("status")
    @Expose
    private String status;

    public String getIndent_auto_gen_id() {
        return indent_auto_gen_id;
    }

    public void setIndent_auto_gen_id(String indent_auto_gen_id) {
        this.indent_auto_gen_id = indent_auto_gen_id;
    }

    public String getContractor_name() {
        return contractor_name;
    }

    public void setContractor_name(String contractor_name) {
        this.contractor_name = contractor_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proteam.projectstoremanagement.Response.PendingIndentResponse;

import java.util.List;

public class PendingIndentList {

    @SerializedName("indent_list")
    @Expose
    private List<PendingIndentResponse> indent_list = null;

    public List<PendingIndentResponse> getIndent_list() {
        return indent_list;
    }

    public void setIndent_list(List<PendingIndentResponse> indent_list) {
        this.indent_list = indent_list;
    }
}

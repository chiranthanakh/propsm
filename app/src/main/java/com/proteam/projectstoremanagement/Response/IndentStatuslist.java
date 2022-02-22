package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndentStatuslist {

    @SerializedName("boq_indent")
    @Expose
    private List<Indentstatusitems> boq_indent = null;

    public List<Indentstatusitems> getBoq_indent() {
        return boq_indent;
    }

    public void setBoq_indent(List<Indentstatusitems> boq_indent) {
        this.boq_indent = boq_indent;
    }
}

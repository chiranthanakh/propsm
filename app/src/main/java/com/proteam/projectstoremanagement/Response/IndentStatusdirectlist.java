package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndentStatusdirectlist {

    @SerializedName("direct_indent")
    @Expose
    private List<Indentstatusidirecttems> direct_indent = null;

    public List<Indentstatusidirecttems> getDirect_indent() {
        return direct_indent;
    }

    public void setDirect_indent(List<Indentstatusidirecttems> direct_indent) {
        this.direct_indent = direct_indent;
    }
}

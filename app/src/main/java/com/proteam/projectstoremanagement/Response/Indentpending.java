package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Indentpending {

    @SerializedName("indent_list")
    @Expose
    private List<Indentpendingitems1> indent_list = null;


    @SerializedName("indent_boq_list")
    @Expose
    private List<Indentpendingitems2> indent_boq_list = null;

    public List<Indentpendingitems1> getIndent_list() {
        return indent_list;
    }

    public void setIndent_list(List<Indentpendingitems1> indent_list) {
        this.indent_list = indent_list;
    }

    public List<Indentpendingitems2> getIndent_boq_list() {
        return indent_boq_list;
    }

    public void setIndent_boq_list(List<Indentpendingitems2> indent_boq_list) {
        this.indent_boq_list = indent_boq_list;
    }
}

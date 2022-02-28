package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class RaiseIndentPreview {

    @SerializedName("indent_details")
    @Expose
    private ArrayList<RaiseIndentPreviewResponse> indent_details = null;

    @SerializedName("material_details")
    @Expose
    private ArrayList<Raiseintentdataitems> material_details = null;

    public RaiseIndentPreview(ArrayList<RaiseIndentPreviewResponse> indent_details, ArrayList<Raiseintentdataitems> material_details) {
        this.indent_details = indent_details;
        this.material_details = material_details;
    }


    public ArrayList<RaiseIndentPreviewResponse> getIndent_details() {
        return indent_details;
    }

    public void setIndent_details(ArrayList<RaiseIndentPreviewResponse> indent_details) {
        this.indent_details = indent_details;
    }

    public ArrayList<Raiseintentdataitems> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(ArrayList<Raiseintentdataitems> material_details) {
        this.material_details = material_details;
    }
}

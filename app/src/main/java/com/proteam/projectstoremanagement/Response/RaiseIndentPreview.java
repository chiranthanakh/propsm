package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RaiseIndentPreview {

    @SerializedName("indent_details")
    @Expose
    private List<RaiseIndentPreviewResponse> indent_details = null;

    @SerializedName("material_details")
    @Expose
    private List<RaiseIndentPreviewResponse> material_details = null;

    public List<RaiseIndentPreviewResponse> getIndent_details() {
        return indent_details;
    }

    public void setIndent_details(List<RaiseIndentPreviewResponse> indent_details) {
        this.indent_details = indent_details;
    }

    public List<RaiseIndentPreviewResponse> getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(List<RaiseIndentPreviewResponse> material_details) {
        this.material_details = material_details;
    }
}

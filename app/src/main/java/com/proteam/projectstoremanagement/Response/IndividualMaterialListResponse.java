package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class IndividualMaterialListResponse {

    @SerializedName("material_list")
    @Expose
    private List<IndividualMaterialResponseSearlize> material_list = null;

    public List<IndividualMaterialResponseSearlize> getMaterial_list() {
        return material_list;
    }

    public void setMaterial_list(List<IndividualMaterialResponseSearlize> material_list) {
        this.material_list = material_list;
    }
}

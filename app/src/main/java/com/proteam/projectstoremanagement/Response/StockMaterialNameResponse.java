package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockMaterialNameResponse {

    @SerializedName("material_list")
    @Expose
    private List<StockMaterialNameResponse2> material_list = null;

    public List<StockMaterialNameResponse2> getMaterial_list() {
        return material_list;
    }

    public void setMaterial_list(List<StockMaterialNameResponse2> material_list) {
        this.material_list = material_list;
    }
}

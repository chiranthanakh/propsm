package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConsumptionMaterialListResponse {

    @SerializedName("list_of_materials")
    @Expose
    private List<ConsumptionMaterialListItems> list_of_materials = null;

    public List<ConsumptionMaterialListItems> getList_of_materials() {
        return list_of_materials;
    }

    public void setList_of_materials(List<ConsumptionMaterialListItems> list_of_materials) {
        this.list_of_materials = list_of_materials;
    }
}

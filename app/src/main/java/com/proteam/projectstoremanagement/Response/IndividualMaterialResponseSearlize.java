package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IndividualMaterialResponseSearlize {

    @SerializedName("material_id")
    @Expose
    private int material_id;

    @SerializedName("material_manual_id")
    @Expose
    private int material_manual_id;

    @SerializedName("material_name")
    @Expose
    private int material_name;

    public int getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(int material_id) {
        this.material_id = material_id;
    }

    public int getMaterial_manual_id() {
        return material_manual_id;
    }

    public void setMaterial_manual_id(int material_manual_id) {
        this.material_manual_id = material_manual_id;
    }

    public int getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(int material_name) {
        this.material_name = material_name;
    }
}

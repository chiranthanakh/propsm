package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Boqitems {

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("boq_id")
    @Expose
    private String boq_id;

    @SerializedName("qty")
    @Expose
    private String qty;



}

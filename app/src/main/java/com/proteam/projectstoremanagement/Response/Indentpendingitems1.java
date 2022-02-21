package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Indentpendingitems1 {

    @SerializedName("indent_id")
    @Expose
    private String indent_id;

    @SerializedName("indent_auto_gen_id")
    @Expose
    private String indent_auto_gen_id;

    @SerializedName("contractor")
    @Expose
    private String contractor;

    @SerializedName("block")
    @Expose
    private String block;

    @SerializedName("locations")
    @Expose
    private String locations;

    @SerializedName("work_order_no")
    @Expose
    private String work_order_no;

    @SerializedName("indent_date")
    @Expose
    private String indent_date;

    @SerializedName("indent_type")
    @Expose
    private String indent_type;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("remarks")
    @Expose
    private String remarks;

    @SerializedName("contractor_name")
    @Expose
    private String contractor_name;

    @SerializedName("location_name")
    @Expose
    private String location_name;

    @SerializedName("sub_location_name")
    @Expose
    private String sub_location_name;

}

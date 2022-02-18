package com.proteam.projectstoremanagement.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.proteam.projectstoremanagement.Response.Contractorlocation;
import com.proteam.projectstoremanagement.Response.SubLocationRaiseResponse;

import java.util.List;

public class ConSubLocationModel {

    @SerializedName("sub_locations")
    @Expose
    private List<SubLocationRaiseResponse> sub_locations = null;

    public List<SubLocationRaiseResponse> getSub_locations() {
        return sub_locations;
    }

    public void setSub_locations(List<SubLocationRaiseResponse> sub_locations) {
        this.sub_locations = sub_locations;
    }
}


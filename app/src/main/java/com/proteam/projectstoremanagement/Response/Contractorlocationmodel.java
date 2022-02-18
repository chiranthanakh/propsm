package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contractorlocationmodel {

    @SerializedName("contractors")
    @Expose
    private List<Contractorlocation> contractors = null;

    @SerializedName("locations")
    @Expose
    private List<Locationconstructor> locations = null;

    public List<Contractorlocation> getContractors() {
        return contractors;
    }

    public void setContractors(List<Contractorlocation> contractors) {
        this.contractors = contractors;
    }

    public List<Locationconstructor> getLocations() {
        return locations;
    }

    public void setLocations(List<Locationconstructor> locations) {
        this.locations = locations;
    }
}

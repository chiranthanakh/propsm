package com.proteam.projectstoremanagement.Request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Updatepreviewlist {

    @SerializedName("update_preview_details")
    @Expose
    private ArrayList<Updatepreviewitems> update_preview_details = null;


    public Updatepreviewlist(ArrayList<Updatepreviewitems> update_preview_details) {
        this.update_preview_details = update_preview_details;
    }

    public ArrayList<Updatepreviewitems> getUpdate_preview_details() {
        return update_preview_details;
    }

    public void setUpdate_preview_details(ArrayList<Updatepreviewitems> update_preview_details) {
        this.update_preview_details = update_preview_details;
    }
}

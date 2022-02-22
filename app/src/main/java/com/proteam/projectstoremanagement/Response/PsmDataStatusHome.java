package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PsmDataStatusHome {

    @SerializedName("pending")
    @Expose
    private String pending;

    @SerializedName("Approved")
    @Expose
    private String Approved;

    @SerializedName("Rejected")
    @Expose
    private String Rejected;

    @SerializedName("Close")
    @Expose
    private String Close;

    public String getPending() {
        return pending;
    }

    public void setPending(String pending) {
        this.pending = pending;
    }

    public String getApproved() {
        return Approved;
    }

    public void setApproved(String approved) {
        Approved = approved;
    }

    public String getRejected() {
        return Rejected;
    }

    public void setRejected(String rejected) {
        Rejected = rejected;
    }

    public String getClose() {
        return Close;
    }

    public void setClose(String close) {
        Close = close;
    }
}

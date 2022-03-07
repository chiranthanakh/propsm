package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PendingIntentgenaralresponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("riser_id")
    @Expose
    private String riser_id;

    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRiser_id() {
        return riser_id;
    }

    public void setRiser_id(String riser_id) {
        this.riser_id = riser_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

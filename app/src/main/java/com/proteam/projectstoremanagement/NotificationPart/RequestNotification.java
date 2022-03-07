package com.proteam.projectstoremanagement.NotificationPart;

import com.google.gson.annotations.SerializedName;

public class RequestNotification {

    @SerializedName("to") //  "to" changed to token
    private String to;

    @SerializedName("notification")
    private SendNotificatiponmodel sendNotificatiponmodel;

    public String getTo() {
        return to;
    }

    public void setToken(String to) {
        this.to = to;
    }

    public SendNotificatiponmodel getSendNotificatiponmodel() {
        return sendNotificatiponmodel;
    }

    public void setSendNotificatiponmodel(SendNotificatiponmodel sendNotificatiponmodel) {
        this.sendNotificatiponmodel = sendNotificatiponmodel;
    }
}

package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Individualresponse {

    @SerializedName("status")
    @Expose
    private int status;

    @SerializedName("approver_id")
    @Expose
    private int approver_id;

    @SerializedName("approver_mail")
    @Expose
    private int approver_mail;

    @SerializedName("message")
    @Expose
    private int message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(int approver_id) {
        this.approver_id = approver_id;
    }

    public int getApprover_mail() {
        return approver_mail;
    }

    public void setApprover_mail(int approver_mail) {
        this.approver_mail = approver_mail;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }
}

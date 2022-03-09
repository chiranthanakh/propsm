package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ConformRaiseindentresponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("approver_mail")
    @Expose
    private String approver_mail;

    @SerializedName("approver_id")
    @Expose
    private String approver_id;

    @SerializedName("message")
    @Expose
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprover_mail() {
        return approver_mail;
    }

    public void setApprover_mail(String approver_mail) {
        this.approver_mail = approver_mail;
    }

    public String getApprover_id() {
        return approver_id;
    }

    public void setApprover_id(String approver_id) {
        this.approver_id = approver_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

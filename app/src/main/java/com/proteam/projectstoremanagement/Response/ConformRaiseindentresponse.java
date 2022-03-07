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
}

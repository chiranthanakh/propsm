package com.proteam.projectstoremanagement.Request;

public class PendingIntentupdaterequest {

    String indent_id,status,remarks;

    public PendingIntentupdaterequest(String indent_id, String status, String remarks) {
        this.indent_id = indent_id;
        this.status = status;
        this.remarks = remarks;
    }
}

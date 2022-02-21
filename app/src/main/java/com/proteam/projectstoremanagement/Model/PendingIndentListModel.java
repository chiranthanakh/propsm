package com.proteam.projectstoremanagement.Model;

public class PendingIndentListModel {

    private String indentnumber, contractorname, status,id;

    public PendingIndentListModel(String indentnumber, String contractorname, String status, String id) {
        this.indentnumber = indentnumber;
        this.contractorname = contractorname;
        this.status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIndentnumber() {
        return indentnumber;
    }

    public void setIndentnumber(String indentnumber) {
        this.indentnumber = indentnumber;
    }

    public String getContractorname() {
        return contractorname;
    }

    public void setContractorname(String contractorname) {
        this.contractorname = contractorname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

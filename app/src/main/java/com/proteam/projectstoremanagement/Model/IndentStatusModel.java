package com.proteam.projectstoremanagement.Model;

public class IndentStatusModel {
    private String indentnumber, contractorname, status;

    public IndentStatusModel(String indentnumber, String contractorname, String status) {
        this.indentnumber = indentnumber;
        this.contractorname = contractorname;
        this.status = status;
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

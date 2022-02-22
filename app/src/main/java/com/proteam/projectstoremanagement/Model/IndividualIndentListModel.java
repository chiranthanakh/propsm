package com.proteam.projectstoremanagement.Model;

public class IndividualIndentListModel {

    private String individualIndentNo, IndividualcontractorName, IndividualIndentStatus;

    public IndividualIndentListModel(String individualIndentNo, String individualcontractorName, String individualIndentStatus) {
        this.individualIndentNo = individualIndentNo;
        IndividualcontractorName = individualcontractorName;
        IndividualIndentStatus = individualIndentStatus;
    }

    public String getIndividualIndentNo() {
        return individualIndentNo;
    }

    public void setIndividualIndentNo(String individualIndentNo) {
        this.individualIndentNo = individualIndentNo;
    }

    public String getIndividualcontractorName() {
        return IndividualcontractorName;
    }

    public void setIndividualcontractorName(String individualcontractorName) {
        IndividualcontractorName = individualcontractorName;
    }

    public String getIndividualIndentStatus() {
        return IndividualIndentStatus;
    }

    public void setIndividualIndentStatus(String individualIndentStatus) {
        IndividualIndentStatus = individualIndentStatus;
    }
}

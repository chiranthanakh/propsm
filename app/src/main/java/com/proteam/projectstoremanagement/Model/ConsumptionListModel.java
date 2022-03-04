package com.proteam.projectstoremanagement.Model;

public class ConsumptionListModel {


    private String ContractorName, Location, ConsDate,siteConsumption_id;

    public ConsumptionListModel(String contractorName, String location, String consDate, String siteConsumption_id) {
        ContractorName = contractorName;
        Location = location;
        ConsDate = consDate;
        this.siteConsumption_id = siteConsumption_id;
    }

    public String getContractorName() {
        return ContractorName;
    }

    public void setContractorName(String contractorName) {
        ContractorName = contractorName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getConsDate() {
        return ConsDate;
    }

    public void setConsDate(String consDate) {
        ConsDate = consDate;
    }

    public String getSiteConsumption_id() {
        return siteConsumption_id;
    }

    public void setSiteConsumption_id(String siteConsumption_id) {
        this.siteConsumption_id = siteConsumption_id;
    }
}





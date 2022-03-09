package com.proteam.projectstoremanagement.Model;

public class IndentStatusModel {
    private String indentnumber, contractorname,status,indent_id,location_name,sub_location_name,contractor,block,locations,indent_date,store_id;

    public IndentStatusModel(String indentnumber, String contractorname, String status, String indent_id, String location_name, String sub_location_name, String contractor, String block, String locations, String indent_date, String store_id) {
        this.indentnumber = indentnumber;
        this.contractorname = contractorname;
        this.status = status;
        this.indent_id = indent_id;
        this.location_name = location_name;
        this.sub_location_name = sub_location_name;
        this.contractor = contractor;
        this.block = block;
        this.locations = locations;
        this.indent_date = indent_date;
        this.store_id = store_id;
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

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getSub_location_name() {
        return sub_location_name;
    }

    public void setSub_location_name(String sub_location_name) {
        this.sub_location_name = sub_location_name;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getIndent_date() {
        return indent_date;
    }

    public void setIndent_date(String indent_date) {
        this.indent_date = indent_date;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }
}

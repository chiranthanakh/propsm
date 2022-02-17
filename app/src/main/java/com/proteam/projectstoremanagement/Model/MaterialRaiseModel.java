package com.proteam.projectstoremanagement.Model;

public class MaterialRaiseModel {

    private String materialcode, materialname, balance,qty;

    public MaterialRaiseModel() {
    }
    public MaterialRaiseModel(String materialcode, String materialname, String balance,String qty) {
        this.materialcode = materialcode;
        this.materialname = materialname;
        this.balance = balance;
        this.qty = qty;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public String getMaterialname() {
        return materialname;
    }

    public void setMaterialname(String materialname) {
        this.materialname = materialname;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
}

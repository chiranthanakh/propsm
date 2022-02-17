package com.proteam.projectstoremanagement.Model;

public class MaterialModel {

    private String materialcode, materialname, closingstock;

    public MaterialModel() {
    }
    public MaterialModel(String materialcode, String materialname, String closingstock) {
        this.materialcode = materialcode;
        this.materialname = materialname;
        this.closingstock = closingstock;
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

    public String getClosingstock() {
        return closingstock;
    }

    public void setClosingstock(String closingstock) {
        this.closingstock = closingstock;
    }
}

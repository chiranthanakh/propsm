package com.proteam.projectstoremanagement.Model;

public class IndividualIndentMaterialModel {

    String materialCode, materialName,materialQty;

    public IndividualIndentMaterialModel(String materialCode, String materialName, String materialQty) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.materialQty = materialQty;
    }

    public String getMaterialCode() {
        return materialCode;
    }

    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialQty() {
        return materialQty;
    }

    public void setMaterialQty(String materialQty) {
        this.materialQty = materialQty;
    }
}

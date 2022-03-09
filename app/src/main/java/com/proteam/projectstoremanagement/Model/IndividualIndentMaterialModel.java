package com.proteam.projectstoremanagement.Model;

public class IndividualIndentMaterialModel {

    String materialCode, materialName,indentQty;

    public IndividualIndentMaterialModel(String materialCode, String materialName, String indentQty) {
        this.materialCode = materialCode;
        this.materialName = materialName;
        this.indentQty = indentQty;
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

    public String getIndentQty() {
        return indentQty;
    }

    public void setIndentQty(String indentQty) {
        this.indentQty = indentQty;
    }
}

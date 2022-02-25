package com.proteam.projectstoremanagement.Model;

public class RaiseIndentModel {

    private String materialcode, materialname, boqbalance,raiseqty,material_id;

    public RaiseIndentModel(String materialcode, String materialname, String boqbalance, String raiseqty, String material_id) {
        this.materialcode = materialcode;
        this.materialname = materialname;
        this.boqbalance = boqbalance;
        this.raiseqty = raiseqty;
        this.material_id = material_id;
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

    public String getBoqbalance() {
        return boqbalance;
    }

    public void setBoqbalance(String boqbalance) {
        this.boqbalance = boqbalance;
    }

    public String getRaiseqty() {
        return raiseqty;
    }

    public void setRaiseqty(String raiseqty) {
        this.raiseqty = raiseqty;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }
}

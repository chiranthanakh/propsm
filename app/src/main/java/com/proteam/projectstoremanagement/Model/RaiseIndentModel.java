package com.proteam.projectstoremanagement.Model;

public class RaiseIndentModel {

    private String materialcode, materialname, boqbalance,raiseqty,material_id,closing_stock,issued_stock,consumed_stock;

    public RaiseIndentModel(String materialcode, String materialname, String boqbalance, String raiseqty, String material_id, String closing_stock, String issued_stock, String consumed_stock) {
        this.materialcode = materialcode;
        this.materialname = materialname;
        this.boqbalance = boqbalance;
        this.raiseqty = raiseqty;
        this.material_id = material_id;
        this.closing_stock = closing_stock;
        this.issued_stock = issued_stock;
        this.consumed_stock = consumed_stock;
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

    public String getClosing_stock() {
        return closing_stock;
    }

    public void setClosing_stock(String closing_stock) {
        this.closing_stock = closing_stock;
    }

    public String getIssued_stock() {
        return issued_stock;
    }

    public void setIssued_stock(String issued_stock) {
        this.issued_stock = issued_stock;
    }

    public String getConsumed_stock() {
        return consumed_stock;
    }

    public void setConsumed_stock(String consumed_stock) {
        this.consumed_stock = consumed_stock;
    }
}

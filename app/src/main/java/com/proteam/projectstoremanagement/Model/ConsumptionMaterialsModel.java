package com.proteam.projectstoremanagement.Model;

public class ConsumptionMaterialsModel {

    private String material_manual_id, material_name, issued_qty;

    public ConsumptionMaterialsModel(String material_manual_id, String material_name, String issued_qty) {
        this.material_manual_id = material_manual_id;
        this.material_name = material_name;
        this.issued_qty = issued_qty;
    }

    public String getMaterial_manual_id() {
        return material_manual_id;
    }

    public void setMaterial_manual_id(String material_manual_id) {
        this.material_manual_id = material_manual_id;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public String getIssued_qty() {
        return issued_qty;
    }

    public void setIssued_qty(String issued_qty) {
        this.issued_qty = issued_qty;
    }
}

package com.proteam.projectstoremanagement.Model;

public class ConsumptionMaterialsModel {

    private String material_manual_id, material_name, issued_qty,material_id,last_updated_qty;

    public ConsumptionMaterialsModel(String material_manual_id, String material_name, String issued_qty, String material_id, String last_updated_qty) {
        this.material_manual_id = material_manual_id;
        this.material_name = material_name;
        this.issued_qty = issued_qty;
        this.material_id = material_id;
        this.last_updated_qty = last_updated_qty;
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

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getLast_updated_qty() {
        return last_updated_qty;
    }

    public void setLast_updated_qty(String last_updated_qty) {
        this.last_updated_qty = last_updated_qty;
    }
}

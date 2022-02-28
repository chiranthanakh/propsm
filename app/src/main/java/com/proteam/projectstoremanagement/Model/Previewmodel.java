package com.proteam.projectstoremanagement.Model;

public class Previewmodel {

    String material_id,indent_qty,boq_qtyString,indent_id,boq_balance_qty,material_manual_id,material_name,closing_stock;


    public Previewmodel(String material_id, String indent_qty, String boq_qtyString, String indent_id, String boq_balance_qty, String material_manual_id, String material_name, String closing_stock) {
        this.material_id = material_id;
        this.indent_qty = indent_qty;
        this.boq_qtyString = boq_qtyString;
        this.indent_id = indent_id;
        this.boq_balance_qty = boq_balance_qty;
        this.material_manual_id = material_manual_id;
        this.material_name = material_name;
        this.closing_stock = closing_stock;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getIndent_qty() {
        return indent_qty;
    }

    public void setIndent_qty(String indent_qty) {
        this.indent_qty = indent_qty;
    }

    public String getBoq_qtyString() {
        return boq_qtyString;
    }

    public void setBoq_qtyString(String boq_qtyString) {
        this.boq_qtyString = boq_qtyString;
    }

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }

    public String getBoq_balance_qty() {
        return boq_balance_qty;
    }

    public void setBoq_balance_qty(String boq_balance_qty) {
        this.boq_balance_qty = boq_balance_qty;
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

    public String getClosing_stock() {
        return closing_stock;
    }

    public void setClosing_stock(String closing_stock) {
        this.closing_stock = closing_stock;
    }
}

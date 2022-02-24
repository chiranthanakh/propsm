package com.proteam.projectstoremanagement.Model;

public class MaterialSModel {

    private String material_manual_id,material_name,closing_stock,favorite_id;

    public MaterialSModel(String material_manual_id, String material_name, String closing_stock, String favorite_id) {
        this.material_manual_id = material_manual_id;
        this.material_name = material_name;
        this.closing_stock = closing_stock;
        this.favorite_id = favorite_id;
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

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }
}

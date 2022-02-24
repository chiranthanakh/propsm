package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MaterialStockResponse {

    @SerializedName("favorite_id")
    @Expose
    private String favorite_id;

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("closing_stock")
    @Expose
    private String closing_stock;

    public String getFavorite_id() {
        return favorite_id;
    }

    public void setFavorite_id(String favorite_id) {
        this.favorite_id = favorite_id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
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

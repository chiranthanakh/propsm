package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Indentpendingitems2 {

    @SerializedName("indent_det_id")
    @Expose
    private String indent_det_id;

    @SerializedName("material_id")
    @Expose
    private String material_id;

    @SerializedName("indent_qty")
    @Expose
    private String indent_qty;

    @SerializedName("boq_qty")
    @Expose
    private String boq_qty;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("indent_id")
    @Expose
    private String indent_id;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("material_manual_id")
    @Expose
    private String material_manual_id;

    @SerializedName("material_name")
    @Expose
    private String material_name;

    @SerializedName("balance_boq")
    @Expose
    private String balance_boq;

    @SerializedName("boq_closing_stock")
    @Expose
    private String boq_closing_stock;

    @SerializedName("boq_net_issue")
    @Expose
    private String boq_net_issue;

    @SerializedName("boq_consumption_qty")
    @Expose
    private String boq_consumption_qty;

    public String getIndent_det_id() {
        return indent_det_id;
    }

    public void setIndent_det_id(String indent_det_id) {
        this.indent_det_id = indent_det_id;
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

    public String getBoq_qty() {
        return boq_qty;
    }

    public void setBoq_qty(String boq_qty) {
        this.boq_qty = boq_qty;
    }

    public String getStore_id() {
        return store_id;
    }

    public void setStore_id(String store_id) {
        this.store_id = store_id;
    }

    public String getIndent_id() {
        return indent_id;
    }

    public void setIndent_id(String indent_id) {
        this.indent_id = indent_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
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

    public String getBalance_boq() {
        return balance_boq;
    }

    public void setBalance_boq(String balance_boq) {
        this.balance_boq = balance_boq;
    }

    public String getBoq_closing_stock() {
        return boq_closing_stock;
    }

    public void setBoq_closing_stock(String boq_closing_stock) {
        this.boq_closing_stock = boq_closing_stock;
    }

    public String getBoq_net_issue() {
        return boq_net_issue;
    }

    public void setBoq_net_issue(String boq_net_issue) {
        this.boq_net_issue = boq_net_issue;
    }

    public String getBoq_consumption_qty() {
        return boq_consumption_qty;
    }

    public void setBoq_consumption_qty(String boq_consumption_qty) {
        this.boq_consumption_qty = boq_consumption_qty;
    }
}

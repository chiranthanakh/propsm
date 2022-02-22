package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("user_id")
    @Expose
    private String user_id;

    @SerializedName("store_id")
    @Expose
    private String store_id;

    @SerializedName("store_manual_id")
    @Expose
    private String store_manual_id;

    @SerializedName("store_name")
    @Expose
    private String store_name;

    @SerializedName("role")
    @Expose
    private String role;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getStore_manual_id() {
        return store_manual_id;
    }

    public void setStore_manual_id(String store_manual_id) {
        this.store_manual_id = store_manual_id;
    }

    public String getStore_name() {
        return store_name;
    }

    public void setStore_name(String store_name) {
        this.store_name = store_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

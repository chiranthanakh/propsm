package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Locationconstructor {

    @SerializedName("block_id")
    @Expose
    private int block_id;

    @SerializedName("block_manual_id")
    @Expose
    private String block_manual_id;

    @SerializedName("block_name")
    @Expose
    private String block_name;

    public int getBlock_id() {
        return block_id;
    }

    public void setBlock_id(int block_id) {
        this.block_id = block_id;
    }

    public String getBlock_manual_id() {
        return block_manual_id;
    }

    public void setBlock_manual_id(String block_manual_id) {
        this.block_manual_id = block_manual_id;
    }

    public String getBlock_name() {
        return block_name;
    }

    public void setBlock_name(String block_name) {
        this.block_name = block_name;
    }
}

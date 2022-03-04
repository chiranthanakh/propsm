package com.proteam.projectstoremanagement.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConsumptionListResponse {

    @SerializedName("client_consumption_list")
    @Expose
    private List<ConsumptionListDataResponse> client_consumption_list = null;

    public List<ConsumptionListDataResponse> getClient_consumption_list() {
        return client_consumption_list;
    }

    public void setClient_consumption_list(List<ConsumptionListDataResponse> client_consumption_list) {
        this.client_consumption_list = client_consumption_list;
    }
}

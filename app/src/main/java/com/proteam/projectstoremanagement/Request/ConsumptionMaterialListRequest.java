package com.proteam.projectstoremanagement.Request;

public class ConsumptionMaterialListRequest {

    String store_id,location_id,sub_location_id,contractor_id;

    public ConsumptionMaterialListRequest(String store_id, String location_id, String sub_location_id, String contractor_id) {
        this.store_id = store_id;
        this.location_id = location_id;
        this.sub_location_id = sub_location_id;
        this.contractor_id = contractor_id;
    }
}

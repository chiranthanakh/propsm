package com.proteam.projectstoremanagement.Request;

public class IndividualDeleteRequest {

    String indent_id,material_id;

    public IndividualDeleteRequest(String indent_id, String material_id) {
        this.indent_id = indent_id;
        this.material_id = material_id;
    }
}

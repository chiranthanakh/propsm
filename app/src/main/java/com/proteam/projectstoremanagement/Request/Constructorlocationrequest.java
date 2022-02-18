package com.proteam.projectstoremanagement.Request;

public class Constructorlocationrequest {

    String email;
    int store_id;

    public Constructorlocationrequest(String email, int store_id) {
        this.email = email;
        this.store_id = store_id;
    }
}

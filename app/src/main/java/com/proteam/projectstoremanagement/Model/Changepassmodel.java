package com.proteam.projectstoremanagement.Model;

public class Changepassmodel {

    String email,old_pass,new_pass;

    public Changepassmodel(String email, String old_pass, String new_pass) {
        this.email = email;
        this.old_pass = old_pass;
        this.new_pass = new_pass;
    }
}

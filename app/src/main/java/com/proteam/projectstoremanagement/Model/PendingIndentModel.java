package com.proteam.projectstoremanagement.Model;

public class PendingIndentModel {

    private String PMaterialcode, PMaterialName, PBalanceboq,PRaisedqty;

    public PendingIndentModel(String PMaterialcode, String PMaterialName, String PBalanceboq, String PRaisedqty) {
        this.PMaterialcode = PMaterialcode;
        this.PMaterialName = PMaterialName;
        this.PBalanceboq = PBalanceboq;
        this.PRaisedqty = PRaisedqty;
    }

    public String getPMaterialcode() {
        return PMaterialcode;
    }

    public void setPMaterialcode(String PMaterialcode) {
        this.PMaterialcode = PMaterialcode;
    }

    public String getPMaterialName() {
        return PMaterialName;
    }

    public void setPMaterialName(String PMaterialName) {
        this.PMaterialName = PMaterialName;
    }

    public String getPBalanceboq() {
        return PBalanceboq;
    }

    public void setPBalanceboq(String PBalanceboq) {
        this.PBalanceboq = PBalanceboq;
    }

    public String getPRaisedqty() {
        return PRaisedqty;
    }

    public void setPRaisedqty(String PRaisedqty) {
        this.PRaisedqty = PRaisedqty;
    }
}

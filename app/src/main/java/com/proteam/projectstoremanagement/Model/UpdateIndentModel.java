package com.proteam.projectstoremanagement.Model;

public class UpdateIndentModel {

    String updatematerialcode,updatematerialname,updatebalanceboq,updateraiseqty;

    public UpdateIndentModel(String updatematerialcode, String updatematerialname, String updatebalanceboq, String updateraiseqty) {
        this.updatematerialcode = updatematerialcode;
        this.updatematerialname = updatematerialname;
        this.updatebalanceboq = updatebalanceboq;
        this.updateraiseqty = updateraiseqty;
    }

    public String getUpdatematerialcode() {
        return updatematerialcode;
    }

    public void setUpdatematerialcode(String updatematerialcode) {
        this.updatematerialcode = updatematerialcode;
    }

    public String getUpdatematerialname() {
        return updatematerialname;
    }

    public void setUpdatematerialname(String updatematerialname) {
        this.updatematerialname = updatematerialname;
    }

    public String getUpdatebalanceboq() {
        return updatebalanceboq;
    }

    public void setUpdatebalanceboq(String updatebalanceboq) {
        this.updatebalanceboq = updatebalanceboq;
    }

    public String getUpdateraiseqty() {
        return updateraiseqty;
    }

    public void setUpdateraiseqty(String updateraiseqty) {
        this.updateraiseqty = updateraiseqty;
    }
}

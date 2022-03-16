package com.proteam.projectstoremanagement.Model;

public class PendingIndentModel {

    private String PMaterialcode, PMaterialName, PBalanceboq,PRaisedqty,closing_stock,issued_qty,consume_qty;

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

    public String getClosing_stock() {
        return closing_stock;
    }

    public void setClosing_stock(String closing_stock) {
        this.closing_stock = closing_stock;
    }

    public String getIssued_qty() {
        return issued_qty;
    }

    public void setIssued_qty(String issued_qty) {
        this.issued_qty = issued_qty;
    }

    public String getConsume_qty() {
        return consume_qty;
    }

    public void setConsume_qty(String consume_qty) {
        this.consume_qty = consume_qty;
    }

    public PendingIndentModel(String PMaterialcode, String PMaterialName, String PBalanceboq, String PRaisedqty, String closing_stock, String issued_qty, String consume_qty) {
        this.PMaterialcode = PMaterialcode;
        this.PMaterialName = PMaterialName;
        this.PBalanceboq = PBalanceboq;
        this.PRaisedqty = PRaisedqty;
        this.closing_stock = closing_stock;
        this.issued_qty = issued_qty;
        this.consume_qty = consume_qty;


    }
}

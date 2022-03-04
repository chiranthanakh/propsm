package com.proteam.projectstoremanagement.Model;

public class ConsumptionDetailsModel {

    private String ConsumptionMaterialCode, ConsumptionMaterialName, ConsumptionQty;

    public ConsumptionDetailsModel(String consumptionMaterialCode, String consumptionMaterialName, String consumptionQty) {
        ConsumptionMaterialCode = consumptionMaterialCode;
        ConsumptionMaterialName = consumptionMaterialName;
        ConsumptionQty = consumptionQty;
    }

    public String getConsumptionMaterialCode() {
        return ConsumptionMaterialCode;
    }

    public void setConsumptionMaterialCode(String consumptionMaterialCode) {
        ConsumptionMaterialCode = consumptionMaterialCode;
    }

    public String getConsumptionMaterialName() {
        return ConsumptionMaterialName;
    }

    public void setConsumptionMaterialName(String consumptionMaterialName) {
        ConsumptionMaterialName = consumptionMaterialName;
    }

    public String getConsumptionQty() {
        return ConsumptionQty;
    }

    public void setConsumptionQty(String consumptionQty) {
        ConsumptionQty = consumptionQty;
    }
}

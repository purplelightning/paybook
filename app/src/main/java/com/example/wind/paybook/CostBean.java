package com.example.wind.paybook;

import java.io.Serializable;

/**
 * Created by wind on 17-7-3.
 */

public class CostBean implements Serializable {

    private String costTitle;
    private String costDate;
    private String costMoney;


//    public CostBean(String costTitle, String costDate, String costMoney) {
//        this.costTitle = costTitle;
//        this.costDate = costDate;
//        this.costMoney = costMoney;
//    }

    public String getCostTitle() {
        return costTitle;
    }

    public String getCostDate() {
        return costDate;
    }

    public String getCostMoney() {
        return costMoney;
    }

    public void setCostTitle(String costTitle) {
        this.costTitle = costTitle;
    }

    public void setCostDate(String costDate) {
        this.costDate = costDate;
    }

    public void setCostMoney(String costMoney) {
        this.costMoney = costMoney;
    }
}

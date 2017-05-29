/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.table;

import java.math.BigDecimal;

/**
 *
 * @author dave
 */
public class ReportsCheckTable {

    private Integer numberCheck;
    private BigDecimal sumCheck;
    private String date;

    public ReportsCheckTable() {
    }

    public ReportsCheckTable(Integer numberCheck, BigDecimal sumCheck, String date) {
        this.numberCheck = numberCheck;
        this.sumCheck = sumCheck;
        this.date = date;
    }

    public Integer getNumberCheck() {
        return numberCheck;
    }

    public void setNumberCheck(Integer numberCheck) {
        this.numberCheck = numberCheck;
    }

    public BigDecimal getSumCheck() {
        return sumCheck;
    }

    public void setSumCheck(BigDecimal sumCheck) {
        this.sumCheck = sumCheck;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

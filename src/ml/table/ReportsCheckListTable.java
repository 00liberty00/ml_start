/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.table;

import java.math.BigDecimal;

/**
 *
 * @author Dave
 */
public class ReportsCheckListTable {

    private Integer number;
    private String name;
    private String code;
    private BigDecimal amount;
    private String date;

    public ReportsCheckListTable() {
    }

    public ReportsCheckListTable(Integer number, String name,String code, BigDecimal amount, String date) {
        this.number = number;
        this.name = name;
        this.amount = amount;
        this.date = date;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

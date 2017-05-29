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
public class ReportsArrivalListTable {

    private String code;
    private String name;
    private BigDecimal amount;
    private BigDecimal priceOpt;
    private BigDecimal price;

    public ReportsArrivalListTable() {
    }

    public ReportsArrivalListTable(String code, String name, BigDecimal amount, BigDecimal priceOpt, BigDecimal price) {
        this.code = code;
        this.name = name;
        this.amount = amount;
        this.priceOpt = priceOpt;
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public BigDecimal getPriceOpt() {
        return priceOpt;
    }

    public void setPriceOpt(BigDecimal priceOpt) {
        this.priceOpt = priceOpt;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

}

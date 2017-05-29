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
public class ReportsCheckListSmallTable {

    private BigDecimal price;
    private String name;
    private BigDecimal amount;

    public ReportsCheckListSmallTable() {
    }

    public ReportsCheckListSmallTable(BigDecimal price, String name, BigDecimal amount) {
        this.price = price;
        this.name = name;
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

}

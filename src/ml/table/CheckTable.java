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
public class CheckTable {
    private String name;
    private String code;
    private BigDecimal amount;
    private BigDecimal price;
    private BigDecimal residue;
    private BigDecimal sum;
    private Boolean checkFreePrice;

    public CheckTable() {
    }
    
    public CheckTable(String name, String code, BigDecimal amount, BigDecimal price, BigDecimal residue, BigDecimal sum, Boolean checkFreePrice) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.price = price;
        this.residue = residue;
        this.sum = sum;
        this.checkFreePrice = checkFreePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }
    
    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public Boolean getCheckFreePrice() {
        return checkFreePrice;
    }

    public void setCheckFreePrice(Boolean checkFreePrice) {
        this.checkFreePrice = checkFreePrice;
    }
    
    
}

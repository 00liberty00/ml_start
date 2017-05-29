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
public class CancelTable {

    private String name;
    private String code;
    private BigDecimal price;
    private BigDecimal amount;
    private BigDecimal residue;

    public CancelTable() {
    }

    public CancelTable(String name, String code, BigDecimal amount, BigDecimal residue, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.amount = amount;
        this.residue = residue;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }

}

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
public class ArrivalTable {

    private String name;
    private String code;
    private BigDecimal amount;
    private BigDecimal oldPrice;
    private BigDecimal newPrice;
    private BigDecimal invoicePrice;
    private BigDecimal residue;

    public ArrivalTable() {
    }

    public ArrivalTable(String name, String code, BigDecimal amount, BigDecimal oldPrice, BigDecimal newPrice, BigDecimal invoicePrice, BigDecimal residue) {
        this.name = name;
        this.code = code;
        this.amount = amount;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.invoicePrice = invoicePrice;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }

    public BigDecimal getInvoicePrice() {
        return invoicePrice;
    }

    public void setInvoicePrice(BigDecimal invoicePrice) {
        this.invoicePrice = invoicePrice;
    }

    public BigDecimal getResidue() {
        return residue;
    }

    public void setResidue(BigDecimal residue) {
        this.residue = residue;
    }

}

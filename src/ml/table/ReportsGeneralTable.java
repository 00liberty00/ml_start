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
public class ReportsGeneralTable {

    private String name;
    private BigDecimal sumPriceAmount;
    private BigDecimal sumProfit;
    private BigDecimal quantitySold;
    private BigDecimal quantityArrival;
    private BigDecimal quantityCancel;
    private BigDecimal balance;

    public ReportsGeneralTable() {
    }

    public ReportsGeneralTable(String name, BigDecimal sumPriceAmount, BigDecimal sumProfit,
            BigDecimal quantitySold, BigDecimal quantityArrival, BigDecimal quantityCancel, BigDecimal balance) {
        this.name = name;
        this.sumPriceAmount = sumPriceAmount;
        this.sumProfit = sumProfit;
        this.quantitySold = quantitySold;
        this.quantityArrival = quantityArrival;
        this.quantityCancel = quantityCancel;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSumPriceAmount() {
        return sumPriceAmount;
    }

    public void setSumPriceAmount(BigDecimal sumPriceAmount) {
        this.sumPriceAmount = sumPriceAmount;
    }

    public BigDecimal getSumProfit() {
        return sumProfit;
    }

    public void setSumProfit(BigDecimal sumProfit) {
        this.sumProfit = sumProfit;
    }

    public BigDecimal getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(BigDecimal quantitySold) {
        this.quantitySold = quantitySold;
    }

    public BigDecimal getQuantityArrival() {
        return quantityArrival;
    }

    public void setQuantityArrival(BigDecimal quantityArrival) {
        this.quantityArrival = quantityArrival;
    }

    public BigDecimal getQuantityCancel() {
        return quantityCancel;
    }

    public void setQuantityCancel(BigDecimal quantityCancel) {
        this.quantityCancel = quantityCancel;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}

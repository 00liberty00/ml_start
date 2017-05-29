/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.table;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author dave
 */
public class ReportsDayTable {

    private BigDecimal sumIn;
    private BigDecimal sumOut;
    private BigDecimal sumInvoice;
    private BigDecimal sumArrival;
    private BigDecimal sumCancel;
    private String note;
    private Date date;

    public ReportsDayTable() {
    }

    public ReportsDayTable(BigDecimal sumIn, BigDecimal sumOut, BigDecimal sumInvoice, BigDecimal sumArrival, BigDecimal sumCancel, String note, Date date) {
        this.sumIn = sumIn;
        this.sumOut = sumOut;
        this.sumInvoice = sumInvoice;
        this.sumArrival = sumArrival;
        this.sumCancel = sumCancel;
        this.note = note;
        this.date = date;
    }

    public BigDecimal getSumIn() {
        return sumIn;
    }

    public void setSumIn(BigDecimal sumIn) {
        this.sumIn = sumIn;
    }

    public BigDecimal getSumOut() {
        return sumOut;
    }

    public void setSumOut(BigDecimal sumOut) {
        this.sumOut = sumOut;
    }

    public BigDecimal getSumInvoice() {
        return sumInvoice;
    }

    public void setSumInvoice(BigDecimal sumInvoice) {
        this.sumInvoice = sumInvoice;
    }

    public BigDecimal getSumArrival() {
        return sumArrival;
    }

    public void setSumArrival(BigDecimal sumArrival) {
        this.sumArrival = sumArrival;
    }

    public BigDecimal getSumCancel() {
        return sumCancel;
    }

    public void setSumCancel(BigDecimal sumCancel) {
        this.sumCancel = sumCancel;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

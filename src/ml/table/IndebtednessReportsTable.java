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
public class IndebtednessReportsTable {

    private Integer id;
    private String note;
    private BigDecimal sum;
    private String user;
    private Date date;

    public IndebtednessReportsTable() {
    }

    public IndebtednessReportsTable(Integer id, String note, BigDecimal sum, String user, Date date) {
        this.id = id;
        this.note = note;
        this.sum = sum;
        this.user = user;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}

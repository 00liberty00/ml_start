/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.table;

/**
 *
 * @author dave
 */
public class ReportsCancelTable {

    private Long numberCancel;
    private String category;
    private String date;

    public ReportsCancelTable() {
    }

    public ReportsCancelTable(Long numberCancel, String category, String date) {
        this.numberCancel = numberCancel;
        this.category = category;
        this.date = date;
    }

    public Long getNumberCancel() {
        return numberCancel;
    }

    public void setNumberCancel(Long numberCancel) {
        this.numberCancel = numberCancel;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}

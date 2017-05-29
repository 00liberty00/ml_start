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
public class ReportsArrivalTable {

    private Long numberArrival;
    private String category;
    private String date;

    public ReportsArrivalTable() {
    }

    public ReportsArrivalTable(Long numberArrival, String category, String date) {
        this.numberArrival = numberArrival;
        this.category = category;
        this.date = date;
    }

    public Long getNumberArrival() {
        return numberArrival;
    }

    public void setNumberArrival(Long numberArrival) {
        this.numberArrival = numberArrival;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import ml.model.Check;
import ml.query.check.DateCheck;
import ml.table.ReportsCheckTable;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class ReportsCheckController111 implements Initializable {

    @FXML
    private DatePicker date;
    @FXML
    private Pane tablePane;
    @FXML
    private Pane checkPane;
    @FXML
    private TableView<ReportsCheckTable> tableReportsCheck;

    @FXML
    private TableColumn<ReportsCheckTable, Integer> numberCheckColumn;
    @FXML
    private TableColumn<ReportsCheckTable, String> dateColumn;
    @FXML
    private TableColumn<ReportsCheckTable, BigDecimal> sumCheckColumn;

    private ObservableList<ReportsCheckTable> reportsCheckData = FXCollections.observableArrayList();
    private DateCheck dateCheck = new DateCheck();
    private List<Check> chList;

    @FXML
    private void getDate() {

        reportsCheckData.clear();           //Очищает таблицу
        dateCheck.setDate(date.getValue().toString());
        chList = dateCheck.displayResult();

        Check cr = null;
        for (Check gg1 : chList) {
            cr = gg1;
            if (cr != null) {
                displayResult(cr);

            }
        }
    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayResult(Check cr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        numberCheckColumn.setCellValueFactory(new PropertyValueFactory<ReportsCheckTable, Integer>("numberCheck"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ReportsCheckTable, String>("date"));
        sumCheckColumn.setCellValueFactory(new PropertyValueFactory<ReportsCheckTable, BigDecimal>("sumCheck"));

        ReportsCheckTable reportsCheckTable = new ReportsCheckTable();
        for (int i = 0; i < 1; i++) {

            reportsCheckTable.setNumberCheck(cr.getIdCheck());
            reportsCheckTable.setDate(sdf.format(cr.getDate()));
            reportsCheckTable.setSumCheck(cr.getSum());

            /*if ("ROLE_ADMIN".equals(auth.toString())) {
            reportsDayTable.setResidue(goods.getResidue());
            }*/
            // заполняем таблицу данными
            reportsCheckData.add(reportsCheckTable);
        }
        tableReportsCheck.setItems(reportsCheckData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        dateCheck.setDate(LocalDate.now().toString());
        chList = dateCheck.displayResult();

        Check cr = null;
        for (Check gg1 : chList) {
            cr = gg1;
            if (cr != null) {
                displayResult(cr);

            }
        }
    }

}

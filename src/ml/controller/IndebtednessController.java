/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import ml.model.Orders;
import ml.query.indebtedness.IndebtByCode;
import ml.query.indebtedness.IndebtList;
import ml.query.indebtedness.UpdateIndebt;
import ml.table.IndebtednessReportsTable;
import ml.window.CashOutWindow;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class IndebtednessController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private DatePicker dateReport;
    @FXML
    private Button okReportsButton;
    @FXML
    private TableView<IndebtednessReportsTable> tableReports;
    @FXML
    private TableColumn<IndebtednessReportsTable, String> noteReportsColumn;
    @FXML
    private TableColumn<IndebtednessReportsTable, BigDecimal> sumReportsColumn;
    @FXML
    private TableColumn<IndebtednessReportsTable, Date> dateReportsColumn;
    @FXML
    private TableColumn<IndebtednessReportsTable, String> recordReportsColumn;

    private List<Orders> crList;
    private ObservableList<IndebtednessReportsTable> indebtednessReportsData = FXCollections.observableArrayList();

    @FXML
    private void getDate(ActionEvent event) {
        //удаление строк в таблице
        for (int i = -1; i < tableReports.getItems().size(); i++) {
            tableReports.getItems().clear();

        }

        getIndebtDate(dateReport.getValue());
    }

    //Погашение/Оплата задолжности/заказа
    @FXML
    private void okReports(ActionEvent event) {
        UpdateIndebt ui = new UpdateIndebt();
        IndebtednessReportsTable i = tableReports.getSelectionModel().getSelectedItem();

        IndebtByCode indebtByCode = new IndebtByCode();
        indebtByCode.setId(i.getId());

        Orders o = indebtByCode.displayResult();

        o.setNote(i.getNote() + " / Погашена(Оплачена)");
        ui.update(o);

        //удаление строк в таблице
        for (int j = -1; j < tableReports.getItems().size(); j++) {
            tableReports.getItems().clear();

        }
        new CashOutWindow(i.getSum().toString(), i.getNote());

        getIndebtDate(dateReport.getValue());

        //new CashOutWindow();
    }

    private void getIndebtDate(LocalDate date) {
        IndebtList indebtList = new IndebtList();
        //indebtList.setDate(date.toString());
        crList = indebtList.listIndebtCategory(date);

        Orders cr = null;

        for (Orders gg1 : crList) {
            cr = gg1;
            if (cr != null) {
                displayResult(cr);
            }
        }
    }

    private void displayResult(Orders orders) {
        noteReportsColumn.setCellValueFactory(new PropertyValueFactory<IndebtednessReportsTable, String>("note"));
        sumReportsColumn.setCellValueFactory(new PropertyValueFactory<IndebtednessReportsTable, BigDecimal>("sum"));
        dateReportsColumn.setCellValueFactory(new PropertyValueFactory<IndebtednessReportsTable, Date>("date"));
        recordReportsColumn.setCellValueFactory(new PropertyValueFactory<IndebtednessReportsTable, String>("user"));

        IndebtednessReportsTable indebtednessReportsTable = new IndebtednessReportsTable();
        for (int i = 0; i < 1; i++) {
            indebtednessReportsTable.setId(orders.getIdOrder());
            indebtednessReportsTable.setNote(orders.getNote());
            indebtednessReportsTable.setSum(orders.getPayment());
            indebtednessReportsTable.setDate(orders.getDate());
            indebtednessReportsTable.setUser(orders.getUserSwing().getName());

            // заполняем таблицу данными
            indebtednessReportsData.add(indebtednessReportsTable);
        }

        tableReports.setItems(indebtednessReportsData);

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Date nowDate = new Date();
                dateReport.setValue(LocalDate.now());

                //getCRDate(LocalDate.now());
            }
        });
    }
}

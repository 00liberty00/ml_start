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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import ml.authentication.GrantedAuth;
import ml.model.ArrivalList;
import ml.model.CaseRecord;
import ml.model.CheckList;
import ml.model.Total;
import ml.query.caserecord.DateCaseRecord;
import ml.query.check.Proceeds;
import ml.query.check.ProfitCheck;
import ml.query.goods.SumGoods;
import ml.query.total.DateTotal;
import ml.table.ReportsDayTable;
import ml.window.ViewArrivalFromReportsWindow;
import ml.window.ViewCancelFromReportsWindow;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class ReportsDayController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private DatePicker dateReport = new DatePicker();
    @FXML
    private Label proceeds;
    @FXML
    private Label cash;
    @FXML
    private Label sumGoods;
    @FXML
    private Label profit;
    @FXML
    private Label cashLabel;
    @FXML
    private Button okReports;
    @FXML
    private TableView<ReportsDayTable> tableReportsDay;
    @FXML
    private TableColumn<ReportsDayTable, BigDecimal> sumInReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, BigDecimal> sumOutReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, BigDecimal> invoiceReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, BigDecimal> arrivalReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, BigDecimal> cancelReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, String> noteReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, Date> dateReportsColumn;
    @FXML
    private TableColumn<ReportsDayTable, Long> numberReportsColumn;

    private ObservableList<ReportsDayTable> reportsDayData = FXCollections.observableArrayList();
    private List<CaseRecord> crList;
    private List<CheckList> checkList;
    private Proceeds pr;
    private GrantedAuth grantedAuth = new GrantedAuth();
    private Object auth = grantedAuth.role();
    private BigDecimal proceedsCheck = new BigDecimal("0.00");          //Выручка по чекам
    private List<ArrivalList> arrivalViewList;

    @FXML
    private void okReportsDay(ActionEvent event) {
    }

    /**
     * Берет дату
     */
    @FXML
    private void getDate() {

        //удаление строк в таблице
        for (int i = -1; i < tableReportsDay.getItems().size(); i++) {
            tableReportsDay.getItems().clear();

        }
        proceeds.setText("0.00");
        profit.setText("0.00");
        sumGoods.setText("0.00");
        cash.setText("0.00");
        getCRDate(dateReport.getValue());
    }

    /**
     * Вовзращает данные отчетов по дням
     *
     * @param code
     */
    private void getCRDate(LocalDate date) {
        pr = new Proceeds();
        DateTotal dateTotal = new DateTotal();
        DateCaseRecord dateCaseRecord = new DateCaseRecord();
        dateCaseRecord.setDate(date);
        //dateCaseRecord.totalSalary(date);
        crList = dateCaseRecord.displayResult();
        BigDecimal sumCashIn = new BigDecimal(0.00);
        BigDecimal sumCashOut = new BigDecimal(0.00);
        ProfitCheck profitCheck = new ProfitCheck();
        SumGoods sumG = new SumGoods();
        BigDecimal profitSum = new BigDecimal(0.00);

        CaseRecord cr = null;

        for (CaseRecord gg1 : crList) {
            cr = gg1;
            if (cr != null) {
                displayResult(cr);
                //Сумма ввода денег в кассу
                if (cr.getCashIn() != null) {
                    sumCashIn = sumCashIn.add(cr.getCashIn().getSumCash());
                }
                //Сумма вывода денег из кассы
                if (cr.getCashOut() != null) {
                    sumCashOut = sumCashOut.add(cr.getCashOut().getSumCash());
                }

            }
        }

        dateTotal.setDate(date.toString());
        Total total = new Total();
        total = dateTotal.displayResult();
        //Возвращает выручку по дате
        //Если дата сегодня, то показывает информацию на сегодняшнюю дату
        if ("ROLE_ADMIN".equals(auth.toString())) {
            if ((date.isEqual(LocalDate.now()) == true) && (total == null)) {
                cashLabel.setText("Наличные");

                cash.setVisible(true);
                pr.setDate(date);
                proceeds.setText(pr.getProceeds().toString());
                cash.setText(pr.getProceeds().add(sumCashIn).subtract(sumCashOut).toString());
                //Расчет прибыли
                checkList = profitCheck.listCheckForProfit(date);
                CheckList check = null;
                for (CheckList c1 : checkList) {
                    profitSum = profitSum.add(c1.getProfit());
                }
                profit.setText(profitSum.toString());
                //Сумма денег в товаре
                sumG.sumGoods();
                sumGoods.setText(sumG.getSumGoods().toString());
            } else {
                //cashLabel.setVisible(false);
                cashLabel.setText("Лишние деньги");
                //cash.setVisible(false);

                if (total != null) {
                    //Возвращает выручку по дате
                    pr.setDate(date);
                    proceedsCheck = pr.getProceeds();
                    proceeds.setText(proceedsCheck.add(total.getSpare()).toString());
                    cash.setText(total.getSpare().toString());
                    sumGoods.setText(total.getSumGoods().toString());
                    profit.setText(total.getProfit().toString());
                }
            }
        }

    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayResult(CaseRecord cr) {
        sumInReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, BigDecimal>("sumIn"));
        sumOutReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, BigDecimal>("sumOut"));
        invoiceReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, BigDecimal>("sumInvoice"));
        if ("ROLE_ADMIN".equals(auth.toString())) {
            arrivalReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, BigDecimal>("sumArrival"));
        }
        cancelReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, BigDecimal>("sumCancel"));
        noteReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, String>("note"));
        dateReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, Date>("date"));
        numberReportsColumn.setCellValueFactory(new PropertyValueFactory<ReportsDayTable, Long>("number"));
        //newPriceArrivalColumn.setCellFactory(TextFieldTableCell.forTableColumn(new BigDecimalStringConverter()));
        /*if ("ROLE_ADMIN".equals(auth.toString())) {
        residueArrivalColumn.setCellValueFactory(new PropertyValueFactory<ArrivalTable, BigDecimal>("residue"));
        }*/
        ReportsDayTable reportsDayTable = new ReportsDayTable();
        for (int i = 0; i < 1; i++) {
            //BigDecimal price = goods.getPrice();
            //BigDecimal amount = decimal("###.###", Double.parseDouble(weight));
            if (cr.getCashIn() != null) {
                reportsDayTable.setSumIn(cr.getCashIn().getSumCash());
                reportsDayTable.setNote(cr.getCashIn().getNote());
            }
            if (cr.getCashOut() != null) {
                reportsDayTable.setSumOut(cr.getCashOut().getSumCash());
                reportsDayTable.setNote(cr.getCashOut().getNote());
            }
            if (cr.getArrival() != null) {
                reportsDayTable.setSumInvoice(cr.getArrival().getSumInvoice());
                reportsDayTable.setNote(cr.getArrival().getNote());
                reportsDayTable.setNumber(cr.getArrival().getIdArrival());
            }
            if ("ROLE_ADMIN".equals(auth.toString())) {
                if (cr.getArrival() != null) {
                    reportsDayTable.setSumArrival(cr.getArrival().getSumArrival());
                    reportsDayTable.setNote(cr.getArrival().getNote());
                    reportsDayTable.setNumber(cr.getArrival().getIdArrival());
                }
            }
            if (cr.getWriteOff() != null) {
                reportsDayTable.setSumCancel(cr.getWriteOff().getSum());
                reportsDayTable.setNote(cr.getWriteOff().getNote());
                reportsDayTable.setNumber(cr.getWriteOff().getIdWriteoff());
            }

            reportsDayTable.setDate(cr.getDate());

            /*if ("ROLE_ADMIN".equals(auth.toString())) {
            reportsDayTable.setResidue(goods.getResidue());
            }*/
            // заполняем таблицу данными
            reportsDayData.add(reportsDayTable);
        }
        tableReportsDay.setItems(reportsDayData);
    }

    @FXML
    private void viewReports() {
        ReportsDayTable table = new ReportsDayTable();
        table = tableReportsDay.getSelectionModel().getSelectedItem();
        if (table.getSumArrival() != null) {
            //new ArrivalReportsWindow(table.getNumber());
            ViewArrivalFromReportsWindow arw = new ViewArrivalFromReportsWindow();
            arw.view(table.getNumber());
        }
        if (table.getSumCancel() != null) {
            ViewCancelFromReportsWindow crw = new ViewCancelFromReportsWindow();
            crw.view(table.getNumber());
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                //Date nowDate = new Date();
                dateReport.setValue(LocalDate.now());
                if ("ROLE_ADMIN".equals(auth.toString())) {
                    arrivalReportsColumn.setVisible(true);
                }
                //getCRDate(LocalDate.now());
            }
        });

    }
}

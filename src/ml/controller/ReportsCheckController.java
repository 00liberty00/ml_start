/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.Ml_FX;
import ml.authentication.GrantedAuth;
import ml.model.Check;
import ml.model.CheckList;
import ml.query.check.CheckListByIdCheck;
import ml.query.check.DateCheck;
import ml.table.ReportsCheckListSmallTable;
import ml.table.ReportsCheckListTable;
import ml.table.ReportsCheckTable;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class ReportsCheckController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private DatePicker date;
    @FXML
    private Label checkNumberLabel;
    @FXML
    private Label nameUser;
    @FXML
    private Label nameUserLabel;
    @FXML
    private Label discountLabel;
    @FXML
    private Label discountText;
    @FXML
    private Label noteText;
    @FXML
    private Button returnGoodButton;
    @FXML
    private TextField numCheckTextField;
    @FXML
    private TextField codeNameGoodTextField;
    @FXML
    private TableView<ReportsCheckTable> tableReportsCheck;
    @FXML
    private TableColumn<ReportsCheckTable, Integer> numberCheckColumn;
    @FXML
    private TableColumn<ReportsCheckTable, String> dateColumn;
    @FXML
    private TableColumn<ReportsCheckTable, BigDecimal> sumCheckColumn;
    @FXML
    private Pane tablePane;
    @FXML
    private Pane checkPane;

    @FXML
    private TableView<ReportsCheckListTable> tableCheckList;
    @FXML
    private TableColumn<ReportsCheckListTable, Integer> numberCol;
    @FXML
    private TableColumn<ReportsCheckListTable, String> dateCol;
    @FXML
    private TableColumn<ReportsCheckListTable, String> nameCol;
    @FXML
    private TableColumn<ReportsCheckListTable, String> codeCol;
    @FXML
    private TableColumn<ReportsCheckListTable, BigDecimal> amountCol;
    @FXML
    private TableColumn<ReportsCheckListTable, BigDecimal> priceCol;

    @FXML
    private TableView<ReportsCheckListSmallTable> tableSmallCheckList;
    @FXML
    private TableColumn<ReportsCheckListSmallTable, BigDecimal> priceSmallCol;
    @FXML
    private TableColumn<ReportsCheckListSmallTable, String> nameSmallCol;
    @FXML
    private TableColumn<ReportsCheckListSmallTable, BigDecimal> amountSmallCol;

    private ObservableList<ReportsCheckTable> reportsCheckData = FXCollections.observableArrayList();
    private ObservableList<ReportsCheckListTable> reportsCheckListData = FXCollections.observableArrayList();
    private ObservableList<ReportsCheckListSmallTable> reportsSmallCheckListData = FXCollections.observableArrayList();

    private BigDecimal discount = new BigDecimal("0.00");
    private Stage primaryStage;
    private GrantedAuth grantedAuth = new GrantedAuth();
    private Object auth = grantedAuth.role();
    private DateCheck dateCheck = new DateCheck();
    private List<Check> chList;
    private List<CheckList> checkViewList;
    private int selectRow = -1;
    private String numCheck = "";

    @FXML
    private void getDate(ActionEvent event) {
        checkPane.setVisible(false);
        tablePane.setVisible(true);
        reportsCheckData.clear();               //Очищает таблицу
        reportsCheckListData.clear();           //Очищает таблицу
        reportsSmallCheckListData.clear();      //Очищает таблицу
        dateCheck.setDate(date.getValue().toString());
        chList = dateCheck.displayResult();

        Check cr = null;
        for (Check gg1 : chList) {
            cr = gg1;
            if (cr != null) {
                displayCheckResult(cr);
                displayCheckListResult(cr);
            }
        }
    }

    @FXML
    private void getCheck(ActionEvent event) {

        //reportsCheckData.clear();           //Очищает таблицу
        reportsCheckListData.clear();           //Очищает таблицу
        checkPane.setVisible(true);
        tablePane.setVisible(false);

    }

    /**
     * Поиск по коду или по названию товара
     */
    @FXML
    private void getCodeNameGood() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<ReportsCheckListTable> filteredData = new FilteredList<>(reportsCheckListData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        codeNameGoodTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<ReportsCheckListTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(tableCheckList.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        tableCheckList.setItems(sortedData);

    }

    /**
     * Просмотр чека
     */
    @FXML
    private void viewCheck() {
        discountText.setText("");
        discount = new BigDecimal("0.00");
        discountLabel.setVisible(false);
        reportsSmallCheckListData.clear();      //Очищает таблицу
        //Значение номера выбранной строки
        CheckListByIdCheck byIdCheck = new CheckListByIdCheck();
        ReportsCheckTable check = tableReportsCheck.getSelectionModel().getSelectedItem();
        if (check != null) {
            checkPane.setVisible(true);
            tablePane.setVisible(false);
            checkNumberLabel.setText(check.getNumberCheck().toString());
            checkViewList = byIdCheck.listCheck(check.getNumberCheck());

            CheckList cr = null;
            for (CheckList gg1 : checkViewList) {
                cr = gg1;
                if (cr != null) {
                    //System.out.println("Проценты равны : " + cr.getCheck().getCheckDiscount().getDiscount().getPercent());
                    displaySmallCheckListResult(cr);
                }
            }
            nameUserLabel.setVisible(true);
            nameUser.setText(cr.getCheck().getUserSwing().getName());

            noteText.setText(cr.getCheck().getNote());

        }
    }

    /**
     * Поиск по номеру чека
     */
    @FXML
    private void getNumCheck() {
        CheckListByIdCheck byIdCheck = new CheckListByIdCheck();
        reportsSmallCheckListData.clear();      //Очищает таблицу
        checkPane.setVisible(true);
        tablePane.setVisible(false);

        numCheck = numCheckTextField.getText();
        checkNumberLabel.setText(numCheck);
        checkViewList = byIdCheck.listCheck(Integer.parseInt(numCheck));

        CheckList cr = null;
        for (CheckList gg1 : checkViewList) {
            cr = gg1;
            if (cr != null) {
                displaySmallCheckListResult(cr);
            }
        }
    }

    /**
     * Фокус на таблице с чеком
     */
    @FXML
    private void focusTable() {
        returnGoodButton.setDisable(false);
    }

    @FXML
    private void returnGood() {
        //Значение номера выбранной строки
        CheckListByIdCheck byIdCheck = new CheckListByIdCheck();
        ReportsCheckTable check = tableReportsCheck.getSelectionModel().getSelectedItem();
        ReportsCheckListSmallTable smallCheck = tableSmallCheckList.getSelectionModel().getSelectedItem();
        checkViewList = byIdCheck.listCheck(check.getNumberCheck());

        if (smallCheck != null) {
            String nameGood = smallCheck.getName();

            try {
                // Load the fxml file and create a new stage for the popup dialog.
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(Ml_FX.class.getResource("/ml/view/ReturnGood.fxml"));

                // Create the dialog Stage.
                Stage dialogStage = new Stage();
                dialogStage.setTitle("Возврат товара");
                dialogStage.initModality(Modality.WINDOW_MODAL);
                dialogStage.initOwner(primaryStage);
                AnchorPane page = (AnchorPane) loader.load();
                Scene scene = new Scene(page);
                dialogStage.setScene(scene);
                ReturnGoodController controller = loader.getController();

                controller.getValue(checkViewList, check.getNumberCheck(), nameGood, discount);
                //controller.setSumText("1000", "10");
                controller.setDialogStage(dialogStage);

                // Show the dialog and wait until the user closes it
                dialogStage.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayCheckResult(Check cr) {

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
     * Метод для просмотра результатов в "JTable".
     */
    private void displayCheckListResult(Check cr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        numberCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, Integer>("number"));
        nameCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, String>("name"));
        codeCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, String>("code"));
        dateCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, String>("date"));
        amountCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, BigDecimal>("amount"));
        priceCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListTable, BigDecimal>("price"));

        ReportsCheckListTable reportsCheckListTable;
        Set<CheckList> s = cr.getCheckLists();
        Iterator<CheckList> it = s.iterator();

        while (it.hasNext()) {
            CheckList checkList = it.next();
            reportsCheckListTable = new ReportsCheckListTable();

            reportsCheckListTable.setNumber(cr.getIdCheck());
            reportsCheckListTable.setDate(sdf.format(cr.getDate()));
            reportsCheckListTable.setAmount(checkList.getAmount());
            reportsCheckListTable.setName(checkList.getGoods().getName());
            reportsCheckListTable.setCode(checkList.getGoods().getCode());

            //Проверка на наличии новой цены
            if (checkList.getNewPrice() == true) {
                //CheckListNewPrice checkListNewPrice = new CheckListNewPrice();
                //checkListNewPrice.setCheckList(checkList);
                reportsCheckListTable.setPrice(checkList.getCheckListNewPrice().getNewPrice());
            } else {
                reportsCheckListTable.setPrice(checkList.getGoods().getPrice());
            }
            // заполняем таблицу данными
            reportsCheckListData.add(reportsCheckListTable);
        }

        tableCheckList.setItems(reportsCheckListData);
    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displaySmallCheckListResult(CheckList cr) {

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        nameSmallCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListSmallTable, String>("name"));
        priceSmallCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListSmallTable, BigDecimal>("price"));
        amountSmallCol.setCellValueFactory(new PropertyValueFactory<ReportsCheckListSmallTable, BigDecimal>("amount"));

        ReportsCheckListSmallTable reportsCheckListSmallTable = new ReportsCheckListSmallTable();
        for (int i = 0; i < 1; i++) {

            reportsCheckListSmallTable.setName(cr.getGoods().getName());
            //System.out.println("Проценты равны : " + cr.getCheck().getCheckDiscount().getDiscount().getPercent());
            //Проверка, был чек со скидкой или нет
            if (cr.getCheck().getCheckDiscount() != null) {
                discount = new BigDecimal(cr.getCheck().getCheckDiscount().getDiscount().getPercent());

                if (cr.getNewPrice() == true) {
                    //CheckListNewPrice checkListNewPrice = new CheckListNewPrice();
                    //checkListNewPrice.setCheckList(checkList);
                    reportsCheckListSmallTable.setPrice(cr.getCheckListNewPrice().getNewPrice().subtract(cr.getCheckListNewPrice().getNewPrice().multiply(discount.divide(new BigDecimal(100)))));
                } else {
                    BigDecimal priceWithPercent = cr.getGoods().getPrice().subtract(cr.getGoods().getPrice().multiply(discount.divide(new BigDecimal(100))));
                    reportsCheckListSmallTable.setPrice(priceWithPercent);
                }

                discountLabel.setVisible(true);
                discountText.setText(discount.toString() + "%");
            } else {
                if (cr.getNewPrice() == true) {
                    //CheckListNewPrice checkListNewPrice = new CheckListNewPrice();
                    //checkListNewPrice.setCheckList(checkList);
                    reportsCheckListSmallTable.setPrice(cr.getCheckListNewPrice().getNewPrice());
                } else {
                    reportsCheckListSmallTable.setPrice(cr.getGoods().getPrice());
                }

            }
            reportsCheckListSmallTable.setAmount(cr.getAmount());
            //System.out.println("" + cr.getGoods().getName());
            /*if ("ROLE_ADMIN".equals(auth.toString())) {
            reportsDayTable.setResidue(goods.getResidue());
            }*/
            // заполняем таблицу данными
            reportsSmallCheckListData.add(reportsCheckListSmallTable);
        }
        tableSmallCheckList.setItems(reportsSmallCheckListData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if ("ROLE_ADMIN".equals(auth.toString())) {
            returnGoodButton.setVisible(true);
            returnGoodButton.setDisable(true);
        } else {
            returnGoodButton.setVisible(false);
        }
        checkPane.setVisible(false);
        tablePane.setVisible(true);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                dateCheck.setDate(LocalDate.now().toString());
                chList = dateCheck.displayResult();
                Check cr = null;
                for (Check gg1 : chList) {
                    cr = gg1;
                    if (cr != null) {
                        displayCheckResult(cr);
                        displayCheckListResult(cr);
                    }
                }

                //Вывод всех продаж по нажатию ESC
                borderPane.setOnKeyPressed(
                        event -> {
                            switch (event.getCode()) {

                                case ESCAPE:
                                    Check crr = null;
                                    for (Check gg1 : chList) {
                                        crr = gg1;
                                        if (crr != null) {
                                            //displayCheckResult(crr);
                                            System.out.println("ПРИВЕТ!!!");
                                            reportsCheckListData.clear();           //Очищает таблицу
                                            checkPane.setVisible(false);
                                            tablePane.setVisible(true);
                                            displayCheckListResult(crr);
                                        }
                                    }
                                    break;

                            }
                        });
                tableReportsCheck.setOnKeyPressed(
                        event -> {
                            switch (event.getCode()) {

                                case ESCAPE:
                                    Check crr = null;
                                    for (Check gg1 : chList) {
                                        crr = gg1;
                                        if (crr != null) {
                                            //displayCheckResult(crr);
                                            System.out.println("ПРИВЕТ!!!");
                                            reportsCheckListData.clear();           //Очищает таблицу
                                            checkPane.setVisible(false);
                                            tablePane.setVisible(true);
                                            displayCheckListResult(crr);
                                        }
                                    }
                                    break;

                            }
                        });

            }
        });

    }

}

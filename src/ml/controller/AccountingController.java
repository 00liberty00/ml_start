/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import ml.model.Goods;
import ml.query.accounting.AccountingQuery;
import ml.query.accounting.BackupGoods;
import ml.query.accounting.CopyGoodsToGoodsAccounting;
import ml.query.accounting.ListGoodsAccounting;
import ml.table.AccountingGoodsTable;

/**
 * Класс учета товара
 *
 * @author Dave
 */
public class AccountingController implements Initializable {

    @FXML
    private TextField codeTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private Text sumOldText;
    @FXML
    private Text sumNewText;
    @FXML
    private Text differentText;
    @FXML
    private Text totalText;
    @FXML
    private Button startButton;
    @FXML
    private Button endButton;
    @FXML
    private Button exitButton;
    @FXML
    private TableView<AccountingGoodsTable> accountingTable;
    @FXML
    private TableColumn<AccountingGoodsTable, String> codeColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, String> nameColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, BigDecimal> priceColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, BigDecimal> residueColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, String> residueFactColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, BigDecimal> residueNewColumn;
    @FXML
    private TableColumn<AccountingGoodsTable, BigDecimal> redisueDifferentColumn;
    @FXML
    private CheckBox checkMode;

    private ObservableList<AccountingGoodsTable> accountingGoodsData = FXCollections.observableArrayList();
    private AccountingQuery accountingQuery = new AccountingQuery();
    private ListGoodsAccounting listGoodsAccounting = new ListGoodsAccounting();
    private boolean startAccounting = false;
    private List<Goods> goodsAccList;
    private int selectRow = -1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //проверка, начат учет или нет(проверка записей в таблице GoodsAccounting)
        accountingQuery.inspectEmptyTable();
        if (accountingQuery.displayInspectTable() == true) {
            startButton.setDisable(false);
            endButton.setDisable(true);
            exitButton.setDisable(true);
            //Таблица неактивна
            accountingTable.setDisable(true);

        } else {
            checkMode.setDisable(false);
            startButton.setDisable(true);
            endButton.setDisable(false);
            exitButton.setDisable(true);
            //Таблица активна
            accountingTable.setDisable(false);
            startAccounting = true;

        }

        //Копирует баз товара в таблицу для учета и в backup
        BackupGoods bg = new BackupGoods();
        CopyGoodsToGoodsAccounting cgtga = new CopyGoodsToGoodsAccounting();

        //Вывод в таблицу из GoodsAccounting
        cgtga.copyGoodsToGoodsAccounting();
        bg.backupGoods();

        goodsAccList = listGoodsAccounting.listGoodsAccounting();
        goodsAccList.forEach((g) -> {
            displayResult(g);
        });

    }

    /**
     * Поиск по коду товара
     * @param event 
     */
    @FXML
    private void getCode() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AccountingGoodsTable> filteredData = new FilteredList<>(accountingGoodsData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        codeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getCode().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } /*else if (person.getName().toLowerCase().contains(lowerCaseFilter)) {
                return true; // Filter matches last name.
                }*/
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<AccountingGoodsTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(accountingTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        accountingTable.setItems(sortedData);
    }

    /**
     * Поиск по названию товара
     * @param event 
     */
    @FXML
    private void getName() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AccountingGoodsTable> filteredData = new FilteredList<>(accountingGoodsData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
        SortedList<AccountingGoodsTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(accountingTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        accountingTable.setItems(sortedData);
    }

    /**
     * Начало учета
     *
     * @param event
     */
    @FXML
    private void startAccounting(ActionEvent event) {

        checkMode.setDisable(false);
        accountingTable.setDisable(false);
        startButton.setDisable(true);
        endButton.setDisable(false);
        exitButton.setDisable(true);
        codeTextField.requestFocus();
        startAccounting = true;
    }

    @FXML
    private void checkMode(ActionEvent event) {

        if (checkMode.isSelected()) {
            startButton.setDisable(true);
            endButton.setDisable(true);
            exitButton.setDisable(true);
            //Таблица неактивна
            accountingTable.setDisable(true);
        } else {
            accountingTable.setDisable(false);
            startButton.setDisable(true);
            endButton.setDisable(false);
            exitButton.setDisable(true);
            codeTextField.requestFocus();
        }
    }

    /**
     * Кол-во товара по факту
     *
     * @param event
     */
    @FXML
    private void getAmountGood(TableColumn.CellEditEvent<AccountingGoodsTable, String> event) {

        String amountString = event.getRowValue().getResidueFact();
        //Замена , на .
        String inv = amountString.replace(',', '.');
        BigDecimal amount = new BigDecimal(inv);
    }

    @FXML
    private void endAccounting(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    private void displayResult(Goods g) {
        codeColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, String>("code"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, String>("name"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, BigDecimal>("price"));
        residueColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, BigDecimal>("residue"));
        residueFactColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, String>("residueFact"));
        residueFactColumn.setCellFactory(TextFieldTableCell.<AccountingGoodsTable>forTableColumn());
        residueNewColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, BigDecimal>("residueNew"));
        redisueDifferentColumn.setCellValueFactory(new PropertyValueFactory<AccountingGoodsTable, BigDecimal>("redisueDifferent"));

        AccountingGoodsTable accGoodsTable = new AccountingGoodsTable();
        //for (Goods g : goodsList) {
        for (int i = 0; i < 1; i++) {

            accGoodsTable.setCode(g.getCode());
            accGoodsTable.setName(g.getName());
            accGoodsTable.setPrice(g.getPrice());
            accGoodsTable.setResidue(g.getResidue());
            //accGoodsTable.setResidueFact();
            //accGoodsTable.setResidueNew(ga.getResidue());

            accountingGoodsData.add(accGoodsTable);
        }

        accountingTable.setItems(accountingGoodsData);
    }

}

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
import ml.model.GoodsAccounting;
import ml.query.accounting.AccountingQuery;
import ml.query.accounting.AllListGoodsAccounting;
import ml.query.accounting.BackupGoods;
import ml.query.accounting.CopyGoodsToGoodsAccounting;
import ml.query.accounting.GoodAccountingByCode;
import ml.query.accounting.ListGoodsForAccounting;
import ml.query.accounting.UpdateResidueGoodAccounting;
import ml.query.goods.GoodByCode;
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
    private ListGoodsForAccounting listGoodsForAccounting = new ListGoodsForAccounting();
    private boolean startAccounting = false;
    private List<Goods> goodsForAccList;
    private List<GoodsAccounting> allGoodsAccList;
    private int selectRow = -1;
    private AllListGoodsAccounting allListGoodsAccounting = new AllListGoodsAccounting();

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
            codeTextField.setDisable(true);
            nameTextField.setDisable(true);
            codeTextField.requestFocus();
        } else {
            checkMode.setDisable(false);
            startButton.setDisable(true);
            endButton.setDisable(false);
            exitButton.setDisable(true);
            //Таблица активна
            accountingTable.setDisable(false);
            startAccounting = true;
            codeTextField.setDisable(false);
            nameTextField.setDisable(false);
            codeTextField.selectAll();
            codeTextField.requestFocus();
        }

        //Копирует баз товара в таблицу для учета и в backup
        BackupGoods bg = new BackupGoods();
        CopyGoodsToGoodsAccounting cgtga = new CopyGoodsToGoodsAccounting();

        //Вывод в таблицу из GoodsAccounting
        cgtga.copyGoodsToGoodsAccounting();
        bg.backupGoods();

        /*goodsForAccList = listGoodsForAccounting.listGoodsForAccounting();
        goodsForAccList.forEach((g) -> {
        displayResult(g, null);
        });*/
        allGoodsAccList = allListGoodsAccounting.allListGoodsForAccounting();
        allGoodsAccList.forEach((ga) -> {
            displayResult(ga);
        });

    }

    /**
     * Поиск по коду товара
     *
     * @param event
     */
    @FXML
    private void getCode() {
        /* FilteredList<AccountingGoodsTable> filteredData = new FilteredList<>(accountingGoodsData, p -> true);
        codeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
        filteredData.setPredicate(person -> {
        if (newValue == null || newValue.isEmpty()) {
        return true;
        }
        
        String lowerCaseFilter = newValue.toLowerCase();
        
        if (person.getCode().toLowerCase().contains(lowerCaseFilter)) {
        return true; // Filter matches first name.
        }
        
        return false; // Does not match.
        });
        });
        
        SortedList<AccountingGoodsTable> sortedData = new SortedList<>(filteredData);
        
        sortedData.comparatorProperty().bind(accountingTable.comparatorProperty());
        
        accountingTable.setItems(sortedData);*/

    }

    /**
     * Работа с полем Код Товара
     *
     * @param event
     */
    @FXML
    private void codeAction(ActionEvent event) {
        //подсчет кол-ва строк в таблице
        int countRow = accountingTable.getItems().size();
        int numberRow = 0;
        String codeFromTable = "";
        String code = codeTextField.getText();
        boolean b = false;

        for (int i = 0; i < countRow; i++) {
            codeFromTable = (String) accountingGoodsData.get(i).getCode();
            if (code.equals(codeFromTable)) {
                b = true;
                numberRow = i;
            }
        }

        //если код в таблице существует, то добавить к кол-ву +1 или +вес
        if (b == true) {
            //weight
            BigDecimal residueNewFromTable = accountingGoodsData.get(numberRow).getResidueNew();
            BigDecimal residueOldFromTable = accountingGoodsData.get(numberRow).getResidue();
            BigDecimal residueDiff;

            // +1 к числу в колонке ИТОГО
            BigDecimal residueNew = accountingGoodsData.get(numberRow).getResidueNew().add(new BigDecimal(1));

            accountingGoodsData.get(numberRow).setResidueNew(residueNew);

            // расчет разницы между "Остаток" и "Итого" и ввод в "Разница"
            residueDiff = residueOldFromTable.subtract(accountingGoodsData.get(numberRow).getResidueNew());
            accountingGoodsData.get(numberRow).setRedisueDifferent(residueDiff);
            //arrivalData.get(row).setNewPrice(allowance.multiply(invoice));s
            //accountingTable.getItems().set(numberRow, accountingGoodsData.get(numberRow));
            accountingTable.refresh();
            //метод +1 к сумме Итого

            getCodePlusOne(code, residueNew, residueDiff);

            codeTextField.selectAll();
            codeTextField.setText("");
            codeTextField.requestFocus();
        } else {
            System.out.println("такой позиции нет");
            codeTextField.selectAll();
            codeTextField.requestFocus();
        }
    }

    /**
     * Сортировка по коду и по названию товара
     *
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
     * При нажатии ENTER в поле НАЗВАНИЕ ИЛИ КОД ТОВАРА переход в ячейку ПО
     * ФАКТУ
     *
     * @param event
     */
    @FXML
    private void onEnter(ActionEvent ae) {
        accountingTable.getFocusModel().focus(0, residueFactColumn);
        accountingTable.requestFocus();

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
        codeTextField.setDisable(false);
        nameTextField.setDisable(false);
        codeTextField.requestFocus();

    }

    @FXML
    private void checkMode(ActionEvent event) {

        if (checkMode.isSelected()) {
            startButton.setDisable(true);
            endButton.setDisable(true);
            exitButton.setDisable(true);
            //Таблица неактивна
            accountingTable.setDisable(true);
            codeTextField.requestFocus();

        } else {
            accountingTable.setDisable(false);
            startButton.setDisable(true);
            endButton.setDisable(false);
            exitButton.setDisable(true);
            codeTextField.requestFocus();
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

        String amountString = event.getNewValue();
        
        
        
        //Замена , на .
        String inv = amountString.replace(',', '.');
        BigDecimal residueDiff;
        BigDecimal amount = new BigDecimal(inv);

        String code = event.getRowValue().getCode();

        nameTextField.setText("");
        nameTextField.requestFocus();

        int countRow = accountingTable.getItems().size();
        int numberRow = 0;

        for (int i = 0; i < countRow; i++) {
            if (code.equals(accountingGoodsData.get(i).getCode())) {
                numberRow = i;
            }
        }

        BigDecimal residueOld = accountingGoodsData.get(numberRow).getResidue();

        // + кол-во к числу в колонке ИТОГО
        BigDecimal residueNew = accountingGoodsData.get(numberRow).getResidueNew().add(amount);
        accountingGoodsData.get(numberRow).setResidueNew(residueNew);

        // расчет разницы между "Остаток" и "Итого" и ввод в "Разница"
        residueDiff = residueOld.subtract(residueNew);
        accountingGoodsData.get(numberRow).setRedisueDifferent(residueDiff);

        //System.out.println("selectRow ^ " + selectRow);
        System.out.println("numberRow ^ " + numberRow);
        System.out.println("residueNew ^ " + residueNew);
        System.out.println("residueOld ^ " + residueOld);
        System.out.println("amountFact ^ " + amount);
        System.out.println("residueDiff ^ " + residueDiff);
        System.out.println("code ^ " + code);
        accountingTable.refresh();
        //       accountingTable.getItems().set(numberRow, accountingGoodsData.get(numberRow));
        //selectRow = -1;

        getCodePlusOne(code, residueNew, residueDiff);
    }

    /**
     * Значение номера выбранной строки
     */
    @FXML
    private void getRow() {
        int i = accountingTable.getItems().size();   // кол-во строк в таблице
        if (i > 0) {
            //TablePosition pos = accountingTable.getSelectionModel().getSelectedCells().get(0);
            //selectRow = pos.getRow();
        }
    }

    @FXML
    private void endAccounting(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    private void displayResult(GoodsAccounting ga) {
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

            accGoodsTable.setCode(ga.getGoods().getCode());
            accGoodsTable.setName(ga.getGoods().getName());
            accGoodsTable.setPrice(ga.getGoods().getPrice());
            accGoodsTable.setResidue(ga.getGoods().getResidue());
            accGoodsTable.setResidueFact("0.00");
            accGoodsTable.setResidueNew(ga.getResidueNew());
            accGoodsTable.setRedisueDifferent(ga.getResidueDiff());
            accountingGoodsData.add(accGoodsTable);
        }

        accountingTable.setItems(accountingGoodsData);
    }

    /**
     * +1 к товару по коду и изменение в таблице SQL разницы
     */
    private void getCodePlusOne(String code, BigDecimal residueNew, BigDecimal residueDiff) {
        GoodByCode goodByCode = new GoodByCode();
        goodByCode.setCode(code);

        GoodAccountingByCode gabc = new GoodAccountingByCode();
        gabc.setCode(goodByCode.displayResult());
        UpdateResidueGoodAccounting updateResidueGoodAccounting = new UpdateResidueGoodAccounting();
        updateResidueGoodAccounting.update(gabc.displayResult(), residueNew, residueDiff);
    }

}

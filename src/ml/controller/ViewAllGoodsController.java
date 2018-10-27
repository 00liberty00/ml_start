/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import ml.model.CategoryGoods;
import ml.model.Goods;
import ml.query.categoryGood.CategoryGoodList;
import ml.query.goods.DeleteGood;
import ml.query.goods.GoodByCode;
import ml.query.goods.GoodsListByCategory;
import ml.query.goods.QueryAllGoodsList;
import ml.query.goods.UpdateGood;
import ml.table.AllGoodsTable;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class ViewAllGoodsController implements Initializable {

    @FXML
    private Button okButton;

    @FXML
    private TableView<AllGoodsTable> allGoodTable;
    @FXML
    private TableColumn<AllGoodsTable, String> codeGoodColumn;
    @FXML
    private TableColumn<AllGoodsTable, String> nameGoodColumn;
    @FXML
    private TableColumn<AllGoodsTable, BigDecimal> purchasePriceColumn;
    @FXML
    private TableColumn<AllGoodsTable, BigDecimal> sellingPriceColumn;
    @FXML
    private TableColumn<AllGoodsTable, BigDecimal> residueColumn;
    @FXML
    private ComboBox<String> categoryGood;
    @FXML
    private TextField searchGoodTextField;
    @FXML
    private MenuItem deleteGood;

    private ObservableList<AllGoodsTable> allGoodData = FXCollections.observableArrayList();
    private QueryAllGoodsList allGoodsList = new QueryAllGoodsList();
    private List<Goods> goodsList;

    private CategoryGoodList categoryGoodList = new CategoryGoodList();
    private List<CategoryGoods> categoryList;
    private String textComboBox;
    private CategoryGoods category = new CategoryGoods();
    private ObservableList<String> options = FXCollections.observableArrayList();
    private GoodsListByCategory goodsListByCategory = new GoodsListByCategory();

    // private ObservableList<AllGoodsTable> goodsListData = FXCollections.observableArrayList();
    /**
     * Обновление кода товара
     *
     * @param event
     */
    @FXML
    private void codeGoodEdit(TableColumn.CellEditEvent<AllGoodsTable, String> event) {
        UpdateGood updateGood = new UpdateGood();
        GoodByCode goodByCode = new GoodByCode();
        Goods g = new Goods();
        //Обновление кода
        goodByCode.setCode(event.getOldValue());
        g = goodByCode.displayResult();
        g.setCode(event.getNewValue());
        updateGood.update(g);

    }

    /**
     * Обновление названия товара
     *
     * @param event
     */
    @FXML
    private void nameGoodEdit(TableColumn.CellEditEvent<AllGoodsTable, String> event) {

        UpdateGood updateGood = new UpdateGood();
        GoodByCode goodByCode = new GoodByCode();
        Goods g = new Goods();
        //Обновление названия
        goodByCode.setCode(event.getRowValue().getCode());
        g = goodByCode.displayResult();
        g.setName(event.getNewValue());
        updateGood.update(g);

        System.out.println("Изменили на: " + event.getNewValue());
    }

    /**
     * Возвращает категорию товара
     *
     * @param event
     */
    @FXML
    private void categoryGoodAction(ActionEvent event) {

        allGoodData.clear();
        for (CategoryGoods cg : categoryList) {
            categoryGood.getValue();

            if ((categoryGood.getValue()).equals(cg.getName())) {
                //удаление строк в таблице
                for (int i = -1; i < allGoodTable.getItems().size(); i++) {
                    allGoodTable.getItems().clear();
                }

                goodsListByCategory.clearList();
                goodsList = goodsListByCategory.listGoods(cg);
                goodsList.forEach((g) -> {
                    displayResult(g);
                });
            }

        }
        if (("Все категории").equals(categoryGood.getValue())) {
            //удаление строк в таблице
            for (int i = -1; i < allGoodTable.getItems().size(); i++) {
                allGoodTable.getItems().clear();
            }

            //allGoodsList.clearList();
            goodsList = allGoodsList.listGoods();
            goodsList.forEach((g) -> {
                displayResult(g);
            });
        }
    }

    @FXML
    private void okButtonAction(ActionEvent event) {
    }

    /**
     * Поиск по коду или по названию товара
     */
    @FXML
    private void getNameCodeGood() {
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<AllGoodsTable> filteredData = new FilteredList<>(allGoodData, p -> true);
        // 2. Set the filter Predicate whenever the filter changes.
        searchGoodTextField.textProperty().addListener((observable, oldValue, newValue) -> {
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
        SortedList<AllGoodsTable> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(allGoodTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        allGoodTable.setItems(sortedData);

    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayResult(Goods g) {
        codeGoodColumn.setCellValueFactory(new PropertyValueFactory<AllGoodsTable, String>("code"));
        codeGoodColumn.setCellFactory(TextFieldTableCell.<AllGoodsTable>forTableColumn());
        nameGoodColumn.setCellValueFactory(new PropertyValueFactory<AllGoodsTable, String>("name"));
        nameGoodColumn.setCellFactory(TextFieldTableCell.<AllGoodsTable>forTableColumn());

        purchasePriceColumn.setCellValueFactory(new PropertyValueFactory<AllGoodsTable, BigDecimal>("purchasePrice"));
        sellingPriceColumn.setCellValueFactory(new PropertyValueFactory<AllGoodsTable, BigDecimal>("sellingPrice"));
        residueColumn.setCellValueFactory(new PropertyValueFactory<AllGoodsTable, BigDecimal>("residue"));

        AllGoodsTable allGoodsTable = new AllGoodsTable();
        //for (Goods g : goodsList) {
        for (int i = 0; i < 1; i++) {

            allGoodsTable.setId(g.getIdGoods());
            allGoodsTable.setCode(g.getCode());
            allGoodsTable.setName(g.getName());
            allGoodsTable.setPurchasePrice(g.getPriceOpt());
            allGoodsTable.setSellingPrice(g.getPrice());
            allGoodsTable.setResidue(g.getResidue());

            allGoodData.add(allGoodsTable);
        }

        allGoodTable.setItems(allGoodData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Запуск отдельного потока
        //threadListGoods.run();

        // TODO
        allGoodsList.clearList();

        goodsList = allGoodsList.listGoods();

        goodsList.forEach((g) -> {
            displayResult(g);
        });

        //Список всего товара
        categoryList = categoryGoodList.getList();
        for (CategoryGoods cg : categoryList) {
            options.add(cg.getName());
        }
        options.add("Все категории");

        categoryGood.setItems(options);

    }

    /**
     * Удаление товара
     */
    @FXML
    private void deletePosition(ActionEvent event) {
        AllGoodsTable table = allGoodTable.getSelectionModel().getSelectedItem();
        String code = table.getCode();
        String name = table.getName();

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Удаление товара!");
        alert.setHeaderText("Удалить данный товар?");
        alert.setContentText(code + " | " + name);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            DeleteGood deleteGood = new DeleteGood();
            GoodByCode goodByCode = new GoodByCode();
            Goods g = new Goods();
            //удаление позиции
            goodByCode.setCode(code);
            g = goodByCode.displayResult();

            deleteGood.delete(g);

            //удаление строк в таблице
            /*for (int i = -1; i < allGoodTable.getItems().size(); i++) {
            allGoodTable.getItems().clear();
            }*/
            allGoodData.clear();
            //allGoodsList.clearList();
            goodsList = allGoodsList.listGoods();
            goodsList.forEach((gg) -> {
                displayResult(gg);
            });
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import ml.model.CategoryGoods;
import ml.model.Check;
import ml.model.CheckList;
import ml.model.GeneralReportsModel;
import ml.model.Goods;
import ml.query.categoryGood.CategoryGoodList;
import ml.query.categoryGood.CategoryGoodsByName;
import ml.query.check.BetweenDatesCheck;
import ml.query.check.CheckListByIdGood;
import ml.query.goods.GoodsListByCategory;
import ml.table.ReportsGeneralTable;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class ReportsGeneralController implements Initializable {

    @FXML
    private TableView<ReportsGeneralTable> tableReportsGeneral;
    @FXML
    private TableColumn<ReportsGeneralTable, String> nameColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> sumPriceAmountColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> sumProfitColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> quantitySoldColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> quantityArrivalColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> quantityCancelColumn;
    @FXML
    private TableColumn<ReportsGeneralTable, BigDecimal> balanceColumn;
    @FXML
    private ComboBox<String> categoryGood;
    @FXML
    private DatePicker dateFrom;
    @FXML
    private DatePicker dateTo;
    @FXML
    private Button okReports;

    private String textComboBox;
    private List<CategoryGoods> categoryList;
    private CheckList chl;
    private List<CheckList> checkList;
    private List<Check> check;

    private CategoryGoodList categoryGoodList = new CategoryGoodList();
    private ObservableList<String> options = FXCollections.observableArrayList();
    private CategoryGoodsByName categoryGoodsByName = new CategoryGoodsByName();
    private CheckListByIdGood checkListByIdGood = new CheckListByIdGood();
    private CategoryGoods categoryGoods = new CategoryGoods();
    private Goods goods = new Goods();
    private List<Goods> listGoods;
    private GoodsListByCategory goodsListByCategory = new GoodsListByCategory();
    private ObservableList<ReportsGeneralTable> reportsGeneralData = FXCollections.observableArrayList();
    private BetweenDatesCheck betweenDatesCheck = new BetweenDatesCheck();
    private GeneralReportsModel generalReportsModel = new GeneralReportsModel();

    @FXML
    private void getCategoryGood(ActionEvent event) {

        //возврат выбранной из списка id_category
        textComboBox = categoryGood.getValue();
        categoryGoodsByName.setCode(textComboBox);
        categoryGoods = categoryGoodsByName.displayResult();
    }

    @FXML
    private void getDateFrom(ActionEvent event) {
        //getDate(dateFrom.getValue());
    }

    @FXML
    private void getDateTo(ActionEvent event) {
        //getDate(dateTo.getValue());
    }

    @FXML
    private void getOk(ActionEvent event) {
        List<String> name = new ArrayList<String>();
        List<BigDecimal> amount = new ArrayList<BigDecimal>();
        List<String> nameNew = new ArrayList<String>();
        List<BigDecimal> amountNew = new ArrayList<BigDecimal>();
        List<BigDecimal> balance = new ArrayList<BigDecimal>();
        List<BigDecimal> balanceNew = new ArrayList<BigDecimal>();
        String n;
        BigDecimal am;
        BigDecimal b;

        //удаление строк в таблице
        for (int i = -1; i < tableReportsGeneral.getItems().size(); i++) {
            tableReportsGeneral.getItems().clear();
        }

        //список чеков по дате
        betweenDatesCheck.setDate(dateFrom.getValue().toString(), dateTo.getValue().toString());
        check = betweenDatesCheck.displayResult();

        //Вывод списка проданного товара по категории
        check.forEach((cg) -> {
            String nameGood = new String();
            Set<CheckList> s = cg.getCheckLists();
            Iterator<CheckList> it = s.iterator();

            while (it.hasNext()) {
                CheckList checkList = it.next();
                checkList.getGoods().getCategoryGoods();
                //Вывод на экран если категориии одинаковы с выбранной категорией
                if (categoryGoods == checkList.getGoods().getCategoryGoods()) {

                    name.add(checkList.getGoods().getName());
                    amount.add(checkList.getAmount());
                    balance.add(checkList.getGoods().getResidue());
                }
            }
        });

        //Запись в новый список названия товара
        for (int i = 0; i < name.size(); i++) {
            n = name.get(i);
            am = amount.get(i);
            b = balance.get(i);
            //если новый список пуст, то записать в него первое название товара и кол-во 0
            if (nameNew.isEmpty()) {
                nameNew.add(n);
                amountNew.add(new BigDecimal(0.00));
                balanceNew.add(b);
            }
            //Запись в новый List не одинаковых названий продуктов
            for (int j = 0; j < nameNew.size(); j++) {

                if (!nameNew.contains(n)) {
                    nameNew.add(n);
                    amountNew.add(new BigDecimal(0.00));
                    balanceNew.add(b);
                }
            }
        }

        //подссчет кол-ва и остатка товара по названию
        for (int i = 0; i < nameNew.size(); i++) {
            String nn = nameNew.get(i);

            for (int j = 0; j < name.size(); j++) {

                if (nn.equals(name.get(j))) {
                    BigDecimal soldGood;
                    soldGood = amountNew.get(i);
                    soldGood = soldGood.add(amount.get(j));
                    amountNew.set(i, soldGood);
                }
            }
        }

        //запись в таблицу
        for (int i = 0; i < nameNew.size(); i++) {

            generalReportsModel.setNameColumn(nameNew.get(i));
            generalReportsModel.setQuantitySoldColumn(new BigDecimal(amountNew.get(i).toString()));
            generalReportsModel.setBalanceColumn(balanceNew.get(i));

            displayResult(generalReportsModel);
        }
    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayResult(GeneralReportsModel generalReportsModel) {

        nameColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, String>("name"));
        sumPriceAmountColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("sumPriceAmount"));
        sumProfitColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("sumProfit"));
        quantitySoldColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("quantitySold"));
        quantityArrivalColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("quantityArrival"));
        quantityCancelColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("quantityCancel"));
        balanceColumn.setCellValueFactory(new PropertyValueFactory<ReportsGeneralTable, BigDecimal>("balance"));

        ReportsGeneralTable reports = new ReportsGeneralTable();
        for (int i = 0; i < 1; i++) {

            reports.setName(generalReportsModel.getNameColumn());
            reports.setQuantitySold(generalReportsModel.getQuantitySoldColumn());
            reports.setBalance(generalReportsModel.getBalanceColumn());
            // заполняем таблицу данными
            reportsGeneralData.add(reports);
        }

        tableReportsGeneral.setItems(reportsGeneralData);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dateFrom.setValue(LocalDate.now());
        dateTo.setValue(LocalDate.now());

        //Список всего товара
        categoryList = categoryGoodList.getList();
        categoryList.forEach((cg) -> {
            options.add(cg.getName());
        });
        categoryGood.setItems(options);
    }

}

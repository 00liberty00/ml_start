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
import ml.model.Arrival;
import ml.model.ArrivalList;
import ml.model.CategoryGoods;
import ml.model.Check;
import ml.model.CheckList;
import ml.model.GeneralReportsModel;
import ml.model.Goods;
import ml.query.arrival.BetweenDatesArrival;
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
    private List<Arrival> arrival;
    private List<ArrivalList> arrivalList;

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
    private BetweenDatesArrival betweenDatesArrival = new BetweenDatesArrival();
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
        List<String> nameCheck = new ArrayList<String>();
        List<BigDecimal> amountCheck = new ArrayList<BigDecimal>();
        List<String> nameNewCheck = new ArrayList<String>();
        List<BigDecimal> amountNewCheck = new ArrayList<BigDecimal>();
        List<String> nameArrival = new ArrayList<String>();
        List<BigDecimal> amountArrival = new ArrayList<BigDecimal>();
        List<String> nameNewArrival = new ArrayList<String>();
        List<BigDecimal> amountNewArrival = new ArrayList<BigDecimal>();
        List<BigDecimal> balance = new ArrayList<BigDecimal>();
        List<BigDecimal> balanceArrival = new ArrayList<BigDecimal>();
        List<BigDecimal> balanceNew = new ArrayList<BigDecimal>();
        List<BigDecimal> profit = new ArrayList<BigDecimal>();
        List<BigDecimal> profitNew = new ArrayList<BigDecimal>();

        String nCheck;
        BigDecimal amCheck;
        String nArrival;
        BigDecimal amArrival;
        BigDecimal pr;
        BigDecimal b;

        //удаление строк в таблице
        for (int i = -1; i < tableReportsGeneral.getItems().size(); i++) {
            tableReportsGeneral.getItems().clear();
        }

        //список чеков по дате
        betweenDatesCheck.setDate(dateFrom.getValue().toString(), dateTo.getValue().toString());
        check = betweenDatesCheck.displayResult();

        //список прихода по дате
        betweenDatesArrival.setDate(dateFrom.getValue().toString(), dateTo.getValue().toString());
        arrival = betweenDatesArrival.displayResult();

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

                    nameCheck.add(checkList.getGoods().getName());
                    amountCheck.add(checkList.getAmount());
                    //amountArrival.add(new BigDecimal(0.00));
                    profit.add(checkList.getProfit());
                    balance.add(checkList.getGoods().getResidue());
                }
            }
        });

        //Вывод списка приходного товара по категории
        arrival.forEach((cg) -> {
            String nameGood = new String();
            Set<ArrivalList> s = cg.getArrivalLists();
            Iterator<ArrivalList> it = s.iterator();

            while (it.hasNext()) {
                ArrivalList arrivalList = it.next();
                arrivalList.getGoods().getCategoryGoods();
                //Вывод на экран если категориии одинаковы с выбранной категорией
                if (categoryGoods == arrivalList.getGoods().getCategoryGoods()) {

                    nameArrival.add(arrivalList.getGoods().getName());
                    amountArrival.add(arrivalList.getAmount());
                    balanceArrival.add(arrivalList.getGoods().getResidue());

                }
            }
        });

        //Запись в новый список названия проданного товара
        for (int i = 0; i < nameCheck.size(); i++) {
            nCheck = nameCheck.get(i);
            amCheck = amountCheck.get(i);
            b = balance.get(i);
            //если новый список пуст, то записать в него первое название товара и кол-во 0
            if (nameNewCheck.isEmpty()) {
                nameNewCheck.add(nCheck);
                amountNewCheck.add(new BigDecimal(0.00));
                amountNewArrival.add(new BigDecimal(0.00));
                profitNew.add(new BigDecimal(0.00));
                balanceNew.add(b);
            }
            //Запись в новый List не одинаковых названий продуктов
            for (int j = 0; j < nameNewCheck.size(); j++) {

                if (!nameNewCheck.contains(nCheck)) {
                    nameNewCheck.add(nCheck);
                    amountNewCheck.add(new BigDecimal(0.00));
                    amountNewArrival.add(new BigDecimal(0.00));
                    profitNew.add(new BigDecimal(0.00));
                    balanceNew.add(b);
                }
            }
        }

        //Запись в новый список названия приходного товара
        for (int i = 0; i < nameArrival.size(); i++) {
            nArrival = nameArrival.get(i);
            amArrival = amountArrival.get(i);
            b = balanceArrival.get(i);
            //если новый список пуст, то записать в него первое название товара и кол-во 0
            if (nameNewCheck.isEmpty()) {
                nameNewCheck.add(nArrival);
                amountNewCheck.add(new BigDecimal(0.00));
                amountNewArrival.add(new BigDecimal(0.00));
                profitNew.add(new BigDecimal(0.00));
                balanceNew.add(b);
            }
            //Запись в новый List не одинаковых названий продуктов
            for (int j = 0; j < nameNewCheck.size(); j++) {

                if (!nameNewCheck.contains(nArrival)) {
                    nameNewCheck.add(nArrival);
                    amountNewCheck.add(new BigDecimal(0.00));
                    amountNewArrival.add(new BigDecimal(0.00));
                    profitNew.add(new BigDecimal(0.00));
                    balanceNew.add(b);
                }
            }
        }

        //подссчет кол-ва и остатка товара по названию
        for (int i = 0; i < nameNewCheck.size(); i++) {
            String nn = nameNewCheck.get(i);

            for (int j = 0; j < nameCheck.size(); j++) {

                if (nn.equals(nameCheck.get(j))) {
                    BigDecimal soldGood;
                    BigDecimal soldProfit;
                    BigDecimal arrivalGood;
                    soldGood = amountNewCheck.get(i);
                    soldGood = soldGood.add(amountCheck.get(j));
                    soldProfit = profitNew.get(i);
                    soldProfit = soldProfit.add(profit.get(j));
                    arrivalGood = amountNewArrival.get(i);
                    arrivalGood = arrivalGood.add(new BigDecimal(0.00));
                    amountNewCheck.set(i, soldGood);
                    profitNew.set(i, soldProfit);
                    amountNewArrival.set(i, arrivalGood);
                }
            }
            for (int j = 0; j < nameArrival.size(); j++) {
                if (nn.equals(nameArrival.get(j))) {
                    System.out.println("вывод : " + nameArrival.get(j));
//                    BigDecimal soldGood;
//                    BigDecimal soldProfit;
                    BigDecimal arrivalGood;
//                    soldGood = amountNewCheck.get(i);
//                    soldGood = soldGood.add(amountCheck.get(j));
//                    soldProfit = profitNew.get(i);
//                    soldProfit = soldProfit.add(profit.get(j));
                    arrivalGood = amountNewArrival.get(i);
                    arrivalGood = arrivalGood.add(amountArrival.get(j));
//                    amountNewCheck.set(i, soldGood);
//                    profitNew.set(i, soldProfit);
                    amountNewArrival.set(i, arrivalGood);
                }
            }
        }

        //запись в таблицу
        for (int i = 0; i < nameNewCheck.size(); i++) {

            generalReportsModel.setNameColumn(nameNewCheck.get(i));
            generalReportsModel.setQuantitySoldColumn(new BigDecimal(amountNewCheck.get(i).toString()));
            generalReportsModel.setQuantityArrivalColumn(new BigDecimal(amountNewArrival.get(i).toString()));
            generalReportsModel.setBalanceColumn(balanceNew.get(i));
            generalReportsModel.setSumProfitColumn(new BigDecimal(profitNew.get(i).toString()));

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
            reports.setQuantityArrival(generalReportsModel.getQuantityArrivalColumn());
            reports.setBalance(generalReportsModel.getBalanceColumn());
            reports.setSumProfit(generalReportsModel.getSumProfitColumn());
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

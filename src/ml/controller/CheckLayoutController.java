/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.Date;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import ml.Ml_FX;
import ml.authentication.GrantedAuth;
import ml.barcode.BarcodeConv;
import ml.model.CategoryFavorite;
import ml.model.Check;
import ml.model.CheckList;
import ml.model.Discount;
import ml.model.Goods;
import ml.model.UserSwing;
import ml.query.discount.NumberDiscount;
import ml.query.favorite.CategoryByName;
import ml.query.favorite.CategoryFavoriteList;
import ml.query.goods.GoodByCode;
import ml.query.goods.QueryAllGoodsList;
import ml.query.user.IdUserByName;
import ml.table.CheckTable;
import ml.util.CheckConnection;
import ml.util.CheckInternetConnection;
import ml.util.RegexpNameGoods;
import ml.xml.XMLSearchAddress;
import org.apache.commons.lang3.ArrayUtils;
import org.controlsfx.control.textfield.TextFields;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * FXML Controller class
 *
 * @author dave
 */
public class CheckLayoutController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Pane checkPane;
    @FXML
    private Label checkLabel;
    @FXML
    private TextField codeGood;
    @FXML
    private TextField sumCheck;
    @FXML
    private TextField sumCheckWithDiscount;
    @FXML
    private Label checkLabel1;
    @FXML
    private TableView<CheckTable> tableCheck;
    @FXML
    private TableColumn<CheckTable, String> codeCheckColumn;
    @FXML
    private TableColumn<CheckTable, String> nameCheckColumn;
    @FXML
    private TableColumn<CheckTable, BigDecimal> priceCheckColumn;
    @FXML
    private TableColumn<CheckTable, BigDecimal> amountCheckColumn;
    @FXML
    private TableColumn<CheckTable, BigDecimal> sumCheckColumn;
    @FXML
    private TableColumn<CheckTable, BigDecimal> residueCheckColumn;
    @FXML
    private TableColumn<CheckTable, Boolean> checkFreePriceCheckColumn;
    @FXML
    private TextField amountGood;
    @FXML
    private Label checkLabel2;
    @FXML
    private Label infoFirstLabel;
    @FXML
    private Label infoSecondLabel;
    @FXML
    private Label checkLabel11;
    @FXML
    private Button firstFavorite;
    @FXML
    private Button secondFavorite;
    @FXML
    private Button thirdFavorite;
    @FXML
    private Button fourthFavorite;
    @FXML
    private Button fifthFavorite;
    @FXML
    private Button sixthFavorite;
    @FXML
    private Button newCheck;
    @FXML
    private Label getConnDB;

    private Stage primaryStage;
    private List<Goods> goodsList;
    private QueryAllGoodsList allGoodsList = new QueryAllGoodsList();
    private List<CategoryFavorite> catFavorList;
    private CategoryFavoriteList favoriteList = new CategoryFavoriteList();
    private CategoryFavorite catFavor = new CategoryFavorite();
    private ObservableList<CheckTable> checkData = FXCollections.observableArrayList();
    private BarcodeConv barcodeConv = new BarcodeConv();
    private String weight = "1";
    private BigDecimal percent = new BigDecimal(0.00);
    private Discount discount = new Discount();
    private GrantedAuth grantedAuth = new GrantedAuth();
    private Object auth = grantedAuth.role();
    private String nameCategory;
    private CategoryFavorite cf;
    private CategoryByName categoryByName = new CategoryByName();
    private RegexpNameGoods regexpNameGoods = new RegexpNameGoods();
    private int selectRow = -1;
    private boolean checkNewPrice = false;
    private BigDecimal newPrice = new BigDecimal(0.00);
    private CheckConnection checkConnection = new CheckConnection();
    private Timeline timeline = new Timeline();
    boolean firstState = false;
    boolean lastState = false;

    /**
     * Поиск товара по коду, по наименованию
     *
     * @param event
     */
    @FXML
    private void getCode(ActionEvent event) {
        String text = codeGood.getText();

        //Проверка первого символа
        if (regexpNameGoods.nameGoods(text) == true) {
            getGoodName(text);
        } else {
            getGoodCode(text);
        }

    }

    /**
     * Возвращает кол-во товара в таблицу чека.
     */
    @FXML
    private void getAmount() {
        String g = amountGood.getText();
        //Замена , на .
        String gWithDot = g.replace(',', '.');

        int i = tableCheck.getItems().size();   // кол-во строк в таблице
        int row = i - 1;                        // последняя строка

        //Если выбрана строка то изменить в ней значение Кол-во
        if (selectRow >= 0) {
            //Кол-во * цена
            BigDecimal price = new BigDecimal(checkData.get(selectRow).getPrice().toString());
            BigDecimal amount = decimal("###.###", Double.parseDouble(gWithDot));
            BigDecimal sum = decimal("###.###", Double.parseDouble(price.multiply(amount).toString()));

            //Вставка данных в последнюю ячейку "кол-во" и "сумма строки" таблицы
            checkData.get(selectRow).setAmount(decimal("##.###", Double.parseDouble(gWithDot)));
            checkData.get(selectRow).setSum(sum);
            tableCheck.getItems().set(selectRow, checkData.get(selectRow));

            tableCheck.getSelectionModel().clearSelection();
            selectRow = -1;
        } else {
            //Кол-во * цена
            BigDecimal price = new BigDecimal(checkData.get(row).getPrice().toString());
            BigDecimal amount = decimal("###.###", Double.parseDouble(gWithDot));
            BigDecimal sum = decimal("###.###", Double.parseDouble(price.multiply(amount).toString()));
            //Вставка данных в последнюю ячейку "кол-во" и "сумма строки" таблицы
            checkData.get(row).setAmount(decimal("##.###", Double.parseDouble(gWithDot)));
            checkData.get(row).setSum(sum);
            tableCheck.getItems().set(row, checkData.get(row));
        }

        //Вывод на экран суммы скидок
        sumForTextField();

        codeGood.requestFocus();
    }

    /**
     * Значение номера выбранной строки
     */
    @FXML
    private void getRow() {
        int i = tableCheck.getItems().size();   // кол-во строк в таблице
        if (i > 0) {
            TablePosition pos = tableCheck.getSelectionModel().getSelectedCells().get(0);
            selectRow = pos.getRow();
        }
    }

    /**
     * Выводит на экран кнопки избранного товара.
     */
    private void getFavButton() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Ml_FX.class.getResource("/ml/view/FavoriteButton.fxml"));

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Избранное");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            FavoriteButtonController controller = loader.getController();
            controller.getButton(cf);
            controller.setDialogStage(dialogStage);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            //System.out.println("Кнопка : " + controller.getTextButtonFav());
            getGoodName(controller.getTextButtonFav());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void category1(ActionEvent event) {
        firstFavorite.setStyle("-fx-base: #b6e7c9;");
        secondFavorite.setStyle("");
        thirdFavorite.setStyle("");
        fourthFavorite.setStyle("");
        fifthFavorite.setStyle("");
        sixthFavorite.setStyle("");

        nameCategory = firstFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();

//        listFavCateg(cf);
    }

    @FXML
    private void category2(ActionEvent event) {
        firstFavorite.setStyle("");
        secondFavorite.setStyle("-fx-base: #b6e7c9;");
        thirdFavorite.setStyle("");
        fourthFavorite.setStyle("");
        fifthFavorite.setStyle("");
        sixthFavorite.setStyle("");
        nameCategory = secondFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();
    }

    @FXML
    private void category3(ActionEvent event) {
        firstFavorite.setStyle("");
        secondFavorite.setStyle("");
        thirdFavorite.setStyle("-fx-base: #b6e7c9;");
        fourthFavorite.setStyle("");
        fifthFavorite.setStyle("");
        sixthFavorite.setStyle("");
        nameCategory = thirdFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();
    }

    @FXML
    private void category4(ActionEvent event) {
        firstFavorite.setStyle("");
        secondFavorite.setStyle("");
        thirdFavorite.setStyle("");
        fourthFavorite.setStyle("-fx-base: #b6e7c9;");
        fifthFavorite.setStyle("");
        sixthFavorite.setStyle("");
        nameCategory = fourthFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();
    }

    @FXML
    private void category5(ActionEvent event) {
        firstFavorite.setStyle("");
        secondFavorite.setStyle("");
        thirdFavorite.setStyle("");
        fourthFavorite.setStyle("");
        fifthFavorite.setStyle("-fx-base: #b6e7c9;");
        sixthFavorite.setStyle("");
        nameCategory = fifthFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();
    }

    @FXML
    private void category6(ActionEvent event) {
        firstFavorite.setStyle("");
        secondFavorite.setStyle("");
        thirdFavorite.setStyle("");
        fourthFavorite.setStyle("");
        fifthFavorite.setStyle("");
        sixthFavorite.setStyle("-fx-base: #b6e7c9;");
        nameCategory = sixthFavorite.getText();
        listButtonName(nameCategory);
        getFavButton();
    }

    /**
     * Выводит на экран сумму столбца СУММА и СУММА СО СКИДКОЙ
     */
    @FXML
    private void sumForTextField() {
        //Сумма столбца СУММА и СУММА СО СКИДКОЙ и вывод на экран
        BigDecimal totalSum = new BigDecimal(0.00);
        BigDecimal totalSumDisc = new BigDecimal(0.00);
        for (CheckTable item : tableCheck.getItems()) {
            totalSum = totalSum.add(item.getSum()).setScale(2, RoundingMode.HALF_UP);
//            totalSum.setScale(2, RoundingMode.HALF_UP);
            if (percent.signum() != 0) {
                totalSumDisc = totalSum.subtract(totalSum.multiply(percent.divide(new BigDecimal(100)))).setScale(2, RoundingMode.HALF_UP);
//                totalSumDisc.setScale(2, RoundingMode.HALF_UP);
                sumCheckWithDiscount.setText(totalSumDisc.toString());
            }
        }
        sumCheck.setText(totalSum.toString());
    }

    @FXML
    private void newCheck() {
        while (tableCheck.getItems().size() > 0) {
            checkData.remove(0);
        }

        infoFirstLabel.setText("");
        infoSecondLabel.setText("");
        percent = new BigDecimal(0.00);
        codeGood.requestFocus();
        discount = new Discount();
        sumCheck.setText("0.00");
        sumCheckWithDiscount.setText("0.00");
        //Список всего товара
        goodsList = allGoodsList.listGoods();
    }

    /**
     * Вовзращает данные о товаре по коду
     *
     * @param code
     */
    private void getGoodCode(String code) {
        boolean checkGood = false;
        Goods goods = null;
        //qg.runQueryCodeGoods(code);
        //goods = qg.displayResultCode();

        for (Goods gg1 : goodsList) {
            if (gg1.getCode().equals(code)) {
                goods = gg1;
                checkGood = true;
            } else {
                checkGood = false;
            }
        }

        if (checkGood == false) {

            boolean firstLetter = barcodeConv.firstLetters(code);
            if (firstLetter == true) {
                String codeBarCode = barcodeConv.sixLetter(code);
                weight = barcodeConv.fourLetter(code);
                if ("0.001".equals(weight)) {
                    weight = "1";
                }
                for (Goods gg1 : goodsList) {
                    if (gg1.getCode().equals(codeBarCode)) {
                        goods = gg1;
                    }
                }
            }

        }
        if (goods != null) {
            displayResult(goods);
        }
        sumForTextField();
        codeGood.requestFocus();
        codeGood.setText(""); //Выделяет текст в поле

    }

    /**
     * Вовзращает данные о товаре по наименованию
     *
     * @param code
     */
    private void getGoodName(String name) {
        Goods goods = null;

        for (Goods gg1 : goodsList) {
            if (gg1.getName().equals(name)) {
                goods = gg1;
            }
        }
        if (goods != null) {
            displayResult(goods);
        }
        sumForTextField();
        codeGood.requestFocus();
        codeGood.setText(""); //Выделяет текст в поле
    }

    /**
     * Конвертирует String в BigDecimal и , в .
     */
    private BigDecimal decimal(String pattern, double value) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        System.out.println(value + "  " + pattern + "  " + output);

        //Замена , на .
        String gWithDot = output.replace(',', '.');
        return new BigDecimal(gWithDot);
    }

    /**
     * Метод для просмотра результатов в "JTable".
     */
    private void displayResult(Goods goods) {
        nameCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, String>("name"));
        codeCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, String>("code"));
        amountCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, BigDecimal>("amount"));
        priceCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, BigDecimal>("price"));
        sumCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, BigDecimal>("sum"));
        checkFreePriceCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, Boolean>("checkFreePrice"));
        if ("ROLE_ADMIN".equals(auth.toString())) {
            residueCheckColumn.setCellValueFactory(new PropertyValueFactory<CheckTable, BigDecimal>("residue"));

        }

        CheckTable checkTable = new CheckTable();
        for (int i = 0; i < 1; i++) {
            BigDecimal price = goods.getPrice();
            BigDecimal amount = decimal("###.###", Double.parseDouble(weight));
            BigDecimal sum = price.multiply(amount);
            checkTable.setCode(goods.getCode());
            checkTable.setName(goods.getName());
            checkTable.setPrice(price);
            checkTable.setAmount(amount);
            checkTable.setSum(sum);
            checkTable.setCheckFreePrice(checkNewPrice);
            if ("ROLE_ADMIN".equals(auth.toString())) {
                checkTable.setResidue(goods.getResidue());
            }

            // заполняем таблицу данными
            if (!"".equals(goods.getName())) {
                checkData.add(checkTable);
            }
        }

        tableCheck.setItems(checkData);

        //Переход курсора на кол-во товара
        //amountGood.requestFocus();
        amountGood.setText(weight);
        amountGood.selectAll(); //Выделяет текст в поле
        weight = "1";
    }

    /**
     * Вызов диалогового окна по нажатию F3.
     */
    private void getDialogDiscount() {

        //StartMain sm = new StartMain();
        //sm.showDiscountDialog();
        NumberDiscount numberDiscount = new NumberDiscount();
        TextInputDialog dialog = new TextInputDialog("Введите номер карты");
        dialog.setTitle("Дисконтная карта");
        dialog.setHeaderText("Дисконтная карта");

        Optional<String> result = dialog.showAndWait();
        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(code -> numberDiscount.numberDiscount(code));
        discount = numberDiscount.displayResult();
        if (discount.getNumcard() != null) {
            percent = decimal("###.###", Double.parseDouble(discount.getPercent()));

            result.ifPresent(numberCard -> infoFirstLabel.setText("Дисконтная карта №" + numberCard));
            infoSecondLabel.setText("Скидка: " + percent + "%");
        } else {
            result.ifPresent(numberCard -> infoFirstLabel.setText("Дисконтная карта №" + numberCard));
            infoSecondLabel.setText("Не существует!!!");
        }
        //discountNumberTextField.setText(percent.toString() + "%");

        //Вывод на экран суммы скидок
        sumForTextField();
    }

    /**
     * Вызов диалогового окна по нажатию CTRL+N.
     */
    private void getDialogFreePrice() {

        //StartMain sm = new StartMain();
        //sm.showDiscountDialog();
        TextInputDialog dialog = new TextInputDialog("Введите свободную цену");
        dialog.setTitle("Свободная цена");
        dialog.setHeaderText("Свободная цена");

        Optional<String> result = dialog.showAndWait();
        infoFirstLabel.setText("Вы ввели новую цену: " + result.get());

        String newPrice = result.get();
        String newPriceWithDot = newPrice.replace(',', '.');

        int i = tableCheck.getItems().size();   // кол-во строк в таблице
        int row = i - 1;                        // последняя строка

        //Кол-во * цена
        BigDecimal price = new BigDecimal(newPriceWithDot);
        //BigDecimal amount = decimal("###.###", Double.parseDouble(gWithDot));
        BigDecimal sum = decimal("###.###", Double.parseDouble(price.multiply(checkData.get(row).getAmount()).toString()));;

        //Вставка данных в последнюю ячейку "кол-во" и "сумма строки" таблицы
        checkData.get(row).setPrice(decimal("##.###", Double.parseDouble(newPriceWithDot)));
        checkData.get(row).setSum(sum);
        checkData.get(row).setCheckFreePrice(true);
        tableCheck.getItems().set(row, checkData.get(row));

        sumForTextField();

        //checkNewPrice = true;
        this.newPrice = price;
        //discountNumberTextField.setText(percent.toString() + "%");
    }

    /**
     * Открывает окно завершения покупки.
     */
    //Вызов диалогового окна по нажатию ESC
    private void getDialogEnd() {

        Check check = new Check();
        Goods goods = new Goods();
        UserSwing userSwing = new UserSwing();

        GoodByCode goodByCode = new GoodByCode();

        IdUserByName idUserByName = new IdUserByName();
        Authentication authentication = SecurityContextHolder.getContext().
                getAuthentication();
        String login = authentication.getName();
        Date date = new Date();
        List<Check> checkArrayList = new ArrayList<>();
        List<CheckList> checkListArrayList = new ArrayList<>();

        BigDecimal sum = new BigDecimal(0.00);
        BigDecimal sumWithDisсount = new BigDecimal(0.00);

        idUserByName.setLoginUser(login);   //метод для idUser по логину
        userSwing = idUserByName.displayResult(); //Возвращает пользователя
        //Получаем значение из таблицы
        int i = tableCheck.getItems().size();   // кол-во строк в таблице

        for (int j = 0; j < i; j++) {
            CheckList checkList = new CheckList();

            if (percent.signum() != 0) {
                System.out.println("Проценты неравны 0!!!");

                BigDecimal price = priceCheckColumn.getCellData(j);
                BigDecimal priceWithDiscount = priceWithDisount(priceCheckColumn.getCellData(j));
                BigDecimal amount = amountCheckColumn.getCellData(j);
                //Сумма чека без скидки
                sum = price.multiply(amount).add(sum);
                //Сумма чека со скидкой
                sumWithDisсount = priceWithDiscount.multiply(amount).add(sumWithDisсount);
                check.setSum(sumWithDisсount);
            } else {
                System.out.println("Проценты равны 0!!!");
                BigDecimal price = priceCheckColumn.getCellData(j);
                BigDecimal amount = amountCheckColumn.getCellData(j);
                sum = price.multiply(amount).add(sum);
                check.setSum(sum);
            }
            //Товар по коду из таблицы
            goodByCode.setCode(codeCheckColumn.getCellData(j));

            Boolean checkFreePrice = checkFreePriceCheckColumn.getCellData(j);

            //Прибыль с одной еденицы товара по коду
            BigDecimal profit = priceCheckColumn.getCellData(j)
                    .subtract(goodByCode.displayResult().getPriceOpt());
            //Прибыль с одного еденицы товара * кол-во
            profit = profit.multiply(amountCheckColumn.getCellData(j));
            //Список товара в чеке

            checkList.setGoods(goodByCode.displayResult());
            goods.getCheckLists().add(checkList);
            checkList.setAmount(amountCheckColumn.getCellData(j));
            checkList.setProfit(profit);

            checkList.setNewPrice(checkFreePrice);

            checkListArrayList.add(checkList);
        }

        //ЧЕК
        check.setDate(date);
        check.setNote(null);
        userSwing.getChecks().add(check);
        check.setUserSwing(userSwing);
        checkArrayList.add(check);

        String convertSum = decimal("###.##", Double.parseDouble(sum.toString())).toString();
        String convertSumWithDiscount = decimal("###.##", Double.parseDouble(sumWithDisсount.toString())).toString();
        boolean okClicked = true;
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Ml_FX.class.getResource("/ml/view/EndCheck.fxml"));

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Конец продажи");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            AnchorPane page = (AnchorPane) loader.load();
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            EndCheckController controller = loader.getController();

            controller.setSumText(convertSum, convertSumWithDiscount);
            //controller.setSumText("1000", "10");
            controller.setDialogStage(dialogStage);

            controller.setAddCheck(tableCheck, checkArrayList, checkListArrayList, discount, checkNewPrice, newPrice);
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            okClicked = controller.displayOkClicked();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Удаляет все строки в таблице
        if (okClicked == true) {
            while (tableCheck.getItems().size() > 0) {
                checkData.remove(0);
            }

            infoFirstLabel.setText("");
            infoSecondLabel.setText("");
            percent = new BigDecimal(0.00);
            codeGood.requestFocus();
            discount = new Discount();
            sumCheck.setText("0.00");
            sumCheckWithDiscount.setText("0.00");
            checkNewPrice = false;
            newPrice = new BigDecimal(0.00);

            //Список всего товара
            goodsList = allGoodsList.listGoods();
        }
        allGoodsList.clearList();

    }

    /**
     * Возвращает цену со скидкой
     *
     * @param price
     * @return
     */
    private BigDecimal priceWithDisount(BigDecimal price) {

        BigDecimal perc = price.multiply(this.percent).divide(new BigDecimal(100));
        price = price.subtract(perc);

        return price;
    }

    /**
     * Список имен для кнопок избранного
     *
     * @param name
     */
    private void listButtonName(String name) {
        cf = new CategoryFavorite();

        categoryByName.setName(name);
        cf = categoryByName.displayResult();
    }

    /**
     * Проверка соединения с БД
     */
    private void getConnection() {

        XMLSearchAddress address = new XMLSearchAddress();
        address.findAddress();
        CheckInternetConnection connection = new CheckInternetConnection();
        /*TimerTask task = new TimerTask() {
        @Override
        public void run() {
        System.out.println("Ответ сети : " + connection.call());
        if ("true".equals(connection.call())) {
        getConnDB.setText("Связь есть");
        } else {
        getConnDB.setText("Связи нет");
        }
        }
        };
        
        timer2.schedule(task, 100, 3000);*/

        timeline = new Timeline(new KeyFrame(Duration.seconds(5), ev -> {

            if ("true".equals(connection.call())) {
                getConnDB.setText("Соединение есть");
                getConnDB.setStyle("-fx-text-fill: BLUE;");
                newCheck.setDisable(false);
                firstFavorite.setDisable(false);
                secondFavorite.setDisable(false);
                thirdFavorite.setDisable(false);
                fourthFavorite.setDisable(false);
                fifthFavorite.setDisable(false);
                sixthFavorite.setDisable(false);

                firstState = true;

            } else {
                getConnDB.setText("Соединения нет");
                getConnDB.setStyle("-fx-text-fill: RED;");
                newCheck.setDisable(true);
                firstFavorite.setDisable(true);
                secondFavorite.setDisable(true);
                thirdFavorite.setDisable(true);
                fourthFavorite.setDisable(true);
                fifthFavorite.setDisable(true);
                sixthFavorite.setDisable(true);
                //checkConnection.closeConnection();
                firstState = false;
                lastState = true;
            }

            if ((firstState == true) && (lastState == true)) {
                firstState = false;
                lastState = false;
                checkConnection.restartConnection();
            }
        }));

        timeline.setCycleCount(100);
        timeline.play();

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                //проверка соединения с БД
                getConnection();
                //Если закрыть окно, то проверка соединения окончена
                Stage stage = (Stage) borderPane.getScene().getWindow();
                stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                    public void handle(WindowEvent we) {
                        timeline.stop();
                    }
                });

                //Список всего товара
                goodsList = allGoodsList.listGoods();

                //Фокус на поле Код товара
                codeGood.requestFocus();

                //Список товара по названию в поле codeGood
                String[] sName = new String[goodsList.size()];
                String[] sCode = new String[goodsList.size()];
                for (int i = 0; i < goodsList.size(); i++) {
                    sName[i] = (String) goodsList.get(i).getName();
                    sCode[i] = (String) goodsList.get(i).getCode();
                }
                String[] both = (String[]) ArrayUtils.addAll(sName, sCode);
                Arrays.sort(both);

                TextFields.bindAutoCompletion(codeGood, both);

                //Список категорий избранного
                catFavorList = favoriteList.listFavoriteCategory();

                if (!catFavorList.isEmpty()) {
                    for (int i = 0; i < catFavorList.size(); i++) {
                        if (i == 0) {
                            firstFavorite.setText(catFavorList.get(0).getName());
                        }
                        if (i == 1) {
                            secondFavorite.setText(catFavorList.get(1).getName());
                        }
                        if (i == 2) {
                            thirdFavorite.setText(catFavorList.get(2).getName());
                        }
                        if (i == 3) {
                            fourthFavorite.setText(catFavorList.get(3).getName());
                        }
                        if (i == 4) {
                            fifthFavorite.setText(catFavorList.get(4).getName());
                        }
                        if (i == 5) {
                            sixthFavorite.setText(catFavorList.get(5).getName());
                        }
                    }
                }
                //Включение диалоговых окон по нажатию клавиш
                borderPane.setOnKeyPressed(
                        event -> {
                            switch (event.getCode()) {
                                case F3:
//                                    if (event.isControlDown()) {
                                    getDialogDiscount();
//                                    }
                                    break;
                                case ESCAPE:
                                    if ("Соединение есть".equals(getConnDB.getText())) {
                                        getDialogEnd();
                                    }
                                    break;

                                case F:
                                    if (event.isControlDown()) {
                                        //getDialogFavofite();
                                    }
                                    break;
                                case HOME:
                                    getDialogFreePrice();
                                    break;
                                case DELETE:
                                    //Удаление строки
                                    TablePosition pos = tableCheck.getSelectionModel().getSelectedCells().get(0);
                                    int row = pos.getRow();
                                    checkData.remove(row);
                                    codeGood.requestFocus();
                                    sumForTextField();
                                    codeGood.requestFocus();
                                    break;
                            }
                        });

                //При нажатии пробела в поле Код товара - переход на кол-во товара
                codeGood.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent event) {
                        switch (event.getCode()) {
                            case SPACE:
                                amountGood.requestFocus();
                                break;
                            case F3:
//                                    if (event.isControlDown()) {
                                getDialogDiscount();
                                System.out.println("КНОПКА F3 НАЖАТА!!!");
//                                    }
                                break;
                            case HOME:
                                getDialogFreePrice();
                                break;
                        }
                    }
                });

                //Список категорий
                /*queryCategoryList.runQueryBasedOnCategory();
                List result = queryCategoryList.displayResult();
                displayButtonCategoryResult(result);*/
            }
        });
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ml.exit.ExitApp;
import ml.window.ArrivalReportsWindow;
import ml.window.ArrivalWindow;
import ml.window.CancelReportsWindow;
import ml.window.CancelWindow;
import ml.window.CashInWindow;
import ml.window.CashOutWindow;
import ml.window.CheckReportsWindow;
import ml.window.CheckWindow;
import ml.window.CreateFavorWindow;
import ml.window.CreateUserWindow;
import ml.window.DayReportsWindow;
import ml.window.EndShiftWindow;
import ml.window.NewSessionWindow;
import ml.window.SettingsPrintCheckWindow;

/**
 * FXML Controller class
 *
 * @author dave
 */
public class RootLayoutController implements Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private Pane sellPane;

    @FXML
    private Pane moneyPane;

    @FXML
    private Pane ordersPane;

    @FXML
    private Pane goodsPane;

    @FXML
    private Pane reportsPane;

    @FXML
    private Pane endShiftPane;

    @FXML
    private Pane settingsPane;

    @FXML
    private Pane exitPane;

    @FXML
    private Button sellMenu;

    @FXML
    private Button moneyMenu;

    @FXML
    private Button ordersMenu;

    @FXML
    private Button goodsMenu;

    @FXML
    private Button reportsMenu;

    @FXML
    private Button endShiftMenu;

    @FXML
    private Button settingsMenu;

    @FXML
    private Button exitMenu;

    @FXML
    private Button cashIn;

    @FXML
    private Button cashOut;

    @FXML
    private Button dayReports;

    @FXML
    private Button checkReports;

    @FXML
    private Button arrivalReports;

    @FXML
    private Button cancelReports;

    @FXML
    private Button endShift;

    @FXML
    private Button newSess;

    @FXML
    private Button exit;
    
    
    private ExitApp app = new ExitApp();

    /**
     * Открывает меню движения товара.
     *
     * @param event
     */
    @FXML
    private void okSellMenuClicked(ActionEvent event) {

        sellPane.setVisible(true);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню движения наличных денег.
     *
     * @param event
     */
    @FXML
    private void okMoneyMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(true);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню продуктов.
     *
     * @param event
     */
    @FXML
    private void okGoodsMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(true);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню заказов.
     *
     * @param event
     */
    @FXML
    private void okOrdersMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(true);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню отчетов.
     *
     * @param event
     */
    @FXML
    private void okReportsMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(true);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню конец смены.
     *
     * @param event
     */
    @FXML
    private void okEndShiftMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(true);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню настроек.
     *
     * @param event
     */
    @FXML
    private void okSettingsMenuClicked(ActionEvent event) {

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(true);
        exitPane.setVisible(false);
    }

    /**
     * Открывает меню настроек.
     *
     * @param event
     */
    @FXML
    private void okExitMenuClicked(ActionEvent event) {
        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(true);
    }

    /**
     * Продажа товара.
     *
     * @param event
     */
    @FXML
    private void okSellClicked(ActionEvent event) {

        new CheckWindow();
    }

    /**
     * Приход товара.
     *
     * @param event
     */
    @FXML
    private void arrivalClicked(ActionEvent event) {

        new ArrivalWindow();
    }

    /**
     * Списание товара
     */
    @FXML
    private void cancelClicked(ActionEvent event) {

        new CancelWindow();
    }

    /**
     * Ввод денежных средств
     */
    @FXML
    private void cashInClicked(ActionEvent event) {

        new CashInWindow();
    }

    /**
     * Вывод денежных средств
     */
    @FXML
    private void cashOutClicked(ActionEvent event) {

        new CashOutWindow();
    }

    /**
     * Дневные отчеты.
     *
     * @param event
     */
    @FXML
    private void dayReportsClicked(ActionEvent event) {

        new DayReportsWindow();
    }

    /**
     * Отчеты продаж.
     *
     * @param event
     */
    @FXML
    private void checkReportsClicked(ActionEvent event) {

        new CheckReportsWindow();
    }

    /**
     * Отчеты прихода.
     *
     * @param event
     */
    @FXML
    private void arrivalReportsClicked(ActionEvent event) {

        new ArrivalReportsWindow();
    }

    /**
     * Отчеты списания.
     *
     * @param event
     */
    @FXML
    private void cancelReportsClicked(ActionEvent event) {

        new CancelReportsWindow();
    }

    /**
     * Конец смены.
     *
     * @param event
     */
    @FXML
    private void endShiftClicked(ActionEvent event) {

        new EndShiftWindow();
    }

    /**
     * Новый пользователь.
     *
     * @param event
     */
    @FXML
    private void newUserClicked(ActionEvent event) {

        new CreateUserWindow();
    }

    /**
     * Создание избранного.
     *
     * @param event
     */
    @FXML
    private void createLikedClicked(ActionEvent event) {

        new CreateFavorWindow();
    }

    /**
     * Настройки печати чека.
     *
     * @param event
     */
    @FXML
    private void settingsPrintCheckClicked(ActionEvent event) {

        new SettingsPrintCheckWindow();
    }

    /**
     * Новая сессия.
     *
     * @param event
     */
    @FXML
    private void newSessClicked(ActionEvent event) {

        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
        new NewSessionWindow();
    }

    /**
     * Выход из приложения.
     *
     * @param event
     */
    @FXML
    private void exitClicked(ActionEvent event) {

        app.close();
    }

    @FXML
    private void handleClose() {
        app.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);
    }
}

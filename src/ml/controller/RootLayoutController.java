/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import ml.authentication.GrantedAuth;
import ml.exit.ExitApp;
import ml.modelLicense.Comp;
import ml.query.goods.QueryAllGoodsList;
import ml.util.CheckConnection;
import ml.util.CheckInternetConnection;
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
import ml.window.GeneralsReportsWindow;
import ml.window.IndebtednessWindow;
import ml.window.NewSessionWindow;
import ml.window.SettingsAppWindow;
import ml.window.SettingsPrintCheckWindow;
import ml.window.SettingsUserWindow;
import ml.window.ViewAllGoodsWindow;

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
    private Button indebtedness;

    @FXML
    private Button dayReports;

    @FXML
    private Button generalsReports;

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

    @FXML
    private VBox vBox;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button viewGoods;

    @FXML
    private Label message;

    private ExitApp app = new ExitApp();
    private GrantedAuth grantedAuth = new GrantedAuth();
    private Object auth = grantedAuth.role();
    private Timeline timeline = new Timeline();
    private CheckConnection checkConnection = new CheckConnection();
    private QueryAllGoodsList allGoodsList = new QueryAllGoodsList();

    private Comp comp = new Comp();
    boolean firstState = false;
    boolean lastState = false;

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
     * Списание товара.
     */
    @FXML
    private void cancelClicked(ActionEvent event) {

        new CancelWindow();
    }

    /**
     * Ввод денежных средств.
     */
    @FXML
    private void cashInClicked(ActionEvent event) {

        new CashInWindow();
    }

    /**
     * Вывод денежных средств.
     */
    @FXML
    private void cashOutClicked(ActionEvent event) {

        new CashOutWindow();
    }

    /**
     * Просмотр задолжности.
     */
    @FXML
    private void indebtednessClicked(ActionEvent event) {

        new IndebtednessWindow();
    }

    /**
     * Просмотр товара - общий список.
     */
    @FXML
    private void viewGoodsClicked(ActionEvent event) {

        if ("ROLE_ADMIN".equals(auth.toString())) {
            new ViewAllGoodsWindow();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Сообщение:");
            alert.setHeaderText("Внимание!");
            alert.setContentText("Требуются права администратора!");
            alert.showAndWait();
        }
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
     * Общие отчеты.
     *
     * @param event
     */
    @FXML
    private void generalsReportsClicked(ActionEvent event) {

        if ("ROLE_ADMIN".equals(auth.toString())) {
            new GeneralsReportsWindow();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Сообщение:");
            alert.setHeaderText("Внимание!");
            alert.setContentText("Требуются права администратора!");
            alert.showAndWait();
        }
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

        if ("ROLE_ADMIN".equals(auth.toString())) {
            new CreateUserWindow();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Сообщение:");
            alert.setHeaderText("Внимание!");
            alert.setContentText("Требуются права администратора!");
            alert.showAndWait();
        }
    }

    /**
     * Настройки пользователя
     * @param event 
     */
    @FXML
    private void settingsUser(ActionEvent event) {
        new SettingsUserWindow();
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

        if ("ROLE_ADMIN".equals(auth.toString())) {
            new SettingsPrintCheckWindow();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Сообщение:");
            alert.setHeaderText("Внимание!");
            alert.setContentText("Требуются права администратора!");
            alert.showAndWait();
        }
    }

    /**
     * Настройки приложения.
     *
     * @param event
     */
    @FXML
    private void settingsAppClicked(ActionEvent event) {

        if ("ROLE_ADMIN".equals(auth.toString())) {
            new SettingsAppWindow();
        } else {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Сообщение:");
            alert.setHeaderText("Внимание!");
            alert.setContentText("Требуются права администратора!");
            alert.showAndWait();
        }
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

        closeApp();
    }

    @FXML
    private void handleClose() {
        closeApp();

    }

    //Подтверждение закрытия окна
    private void closeApp() {
        Alert closeConfirmation = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Вы уверены, что хотите выйти?"
        );
        Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.OK
        );
        Button cancelButton = (Button) closeConfirmation.getDialogPane().lookupButton(
                ButtonType.CANCEL
        );
        exitButton.setText("Да");
        cancelButton.setText("Нет");
        closeConfirmation.setHeaderText("Выход из программы");

        closeConfirmation.initModality(Modality.APPLICATION_MODAL);
        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
        } else {
            app.close();

        }
    }

    public void setCompCard(Comp comp) {
        this.comp = comp;

        System.out.println("");
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CheckInternetConnection connection = new CheckInternetConnection();

        sellPane.setVisible(false);
        moneyPane.setVisible(false);
        ordersPane.setVisible(false);
        goodsPane.setVisible(false);
        reportsPane.setVisible(false);
        //Pane.setVisible(false);
        endShiftPane.setVisible(false);
        settingsPane.setVisible(false);
        exitPane.setVisible(false);

        /*TestTrial testTrial = new TestTrial();
        // Created c = new Created();
        AddUserLicense addUserLicense = new AddUserLicense();
        License license = testTrial.license();*/
        //Сообщение
        timeline = new Timeline(new KeyFrame(Duration.seconds(10), ev -> {
            /*CompMessage compMessage = new CompMessage();
            compMessage.setComp(comp);
            message.setText(compMessage.displayResult().getMessage());*/

            //Проверка связи с бд
            if ("true".equals(connection.call())) {

                firstState = true;

            } else {
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

        //allGoodsList.listGoods();
        //executor.shutdownNow();
        //new NewThread("Do it!").start();
    }

    

}

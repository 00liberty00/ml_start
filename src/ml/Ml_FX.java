/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ml.controller.CreateAdminController;
import ml.controller.CreateDBController;
import ml.controller.LoginController;
import ml.query.auth.AdminExists;
import ml.trial.TestTrial;
import ml.dialog.DialogAlert;
import ml.exit.ExitApp;
import ml.model.Comp;
import ml.query.created.Created;

/**
 *
 * @author dave
 */
public class Ml_FX extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private DialogAlert alert;
    private Created created = new Created();
    private Comp comp = new Comp();

    @Override
    public void start(Stage primaryStage) {

        /*IpMac ipMac = new IpMac();
        ipMac.getIp();
        
        //00-01-02-03-04-05
        String macString = ipMac.getMac().replaceAll("-", "");
        
        //проверка на наличие mac адреса
        boolean checkMac = created.searchMac(macString);
        if (checkMac == false) {
        TrialInfo info = new TrialInfo();
        Date date = new Date();
        String[] macAddressParts = ipMac.getMac().split("-");
        
        byte[] macAddressBytes = new byte[6];
        for (int i = 0; i < 6; i++) {
        Integer hex = Integer.parseInt(macAddressParts[i], 16);
        macAddressBytes[i] = hex.byteValue();
        }
        comp.setMac(macAddressBytes);
        comp.setBlocking(false);
        comp.setDateCreate(date);
        comp.setName(ipMac.getName());
        //\\            comp.setIdLicense();
        //Запись MAC-адреса в БД
        created.insertMac(comp);
        
        }*/
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ML");
        File f = new File("src/hibernate.cfg.xml");
        if (f.exists()) {

            /*int l = 0;
            
            NewTrialClass ntc = new NewTrialClass();
            System.out.println("Дата создания! - " + ntc);
            if (l == 1) {
            
            }*/
 /*if (testTrial.testDateTrial() == false && testTrial.falseTrial() == false) {
            
            System.out.println("Триал версия закончена!!!");
            DialogTextInput dialogTextInput = new DialogTextInput();
            dialogTextInput.dialog("Триал версия закончена!!!", "Внимание! Триал версия закончена!!!", "Введите ключ", "");
            dialogTextInput.start(primaryStage);
            
            String newName = dialogTextInput.display();
            
            String l2 = created.select(newName);
            boolean b = created.useLicense();
            if (newName.equals(l2) && b == false) {
            //Trial trial = new Trial();
            TrialInfo trialInfo = new TrialInfo();
            ChangeCreatedTrial cct = new ChangeCreatedTrial();
            trialInfo.getTrial().setLicense(l2);
            trialInfo.getTrial().setCreated(true);
            cct.update(trialInfo.getTrial());
            created.update();
            //Ввод логина и пароля
            initLoginLayout();
            } else {
            alert = new DialogAlert();
            alert.alert("Информация о ключе", "Внимание!!!", "Номер ключа использован или не существует!");
            
            app.close();
            }
            //alert.alert("Внимание!!!", "", "Доступ запрещен!!! Триал-версия окончена");
            } else {*/
            AdminExists adminExists = new AdminExists();
            boolean adminCheck = adminExists.checkAdminExist();

            //Если админ не создан, то
            if (adminCheck == true) {
                //Создание админа
                initAdminPassLayout();

            } else {
                //Ввод логина и пароля

                initLoginLayout();

            }
            //System.out.println(ipMac.getIp());
            //System.out.println(ipMac.getMac());
            //}
        } else {

            initSettingsDBLayout();

        }

        ///////////
        //Создание админа
        /*AdminExists adminExists = new AdminExists();
        boolean adminCheck = adminExists.checkAdminExist();
        
        //Если админ не создан, то
        if (adminCheck == true) {
        //Создание админа
        initAdminPassLayout();
        
        }*/
        //////////
        //Проверка, есть ли запись админа
        primaryStage.setOnCloseRequest(confirmCloseEventHandler);
    }

    /**
     * Окно создания администратора.
     */
    public void initAdminPassLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Ml_FX.class.getResource("view/CreateAdmin.fxml"));
            AnchorPane loginLayout = (AnchorPane) loader.load();

            CreateAdminController lc = loader.getController();
            lc.setDialogStage(primaryStage);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loginLayout);
            //scene.getStylesheets().add("/styles/login.css");

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Окно создания администратора.
     */
    public void initSettingsDBLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Ml_FX.class.getResource("view/CreateDB.fxml"));
            AnchorPane loginLayout = (AnchorPane) loader.load();

            CreateDBController lc = loader.getController();
            lc.setDialogStage(primaryStage);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loginLayout);
            //scene.getStylesheets().add("/styles/login.css");

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Окно ввода логина и пароля.
     */
    public void initLoginLayout() {

        try {
            ExitApp app = new ExitApp();
            TestTrial testTrial = new TestTrial();
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Ml_FX.class.getResource("view/Login.fxml"));
            AnchorPane loginLayout = (AnchorPane) loader.load();

            LoginController lc = loader.getController();
            lc.setDialogStage(primaryStage);

            // Show the scene containing the root layout.
            Scene scene = new Scene(loginLayout);
            //scene.getStylesheets().add("/styles/login.css");

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает главную сцену.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        launch(args);
    }

    //Перезапуск приложения
    public void update(Observable o, Object arg) {
        Platform.runLater(new Runnable() {
            public void run() {
                new Ml_FX().start(new Stage());
            }

        });
    }

    //Подтверждение закрытия окна
    private EventHandler<WindowEvent> confirmCloseEventHandler = event -> {
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
        closeConfirmation.initOwner(primaryStage);

        // normally, you would just use the default alert positioning,
        // but for this simple sample the main stage is small,
        // so explicitly position the alert so that the main window can still be seen.
        closeConfirmation.setX(primaryStage.getX());
        closeConfirmation.setY(primaryStage.getY() + primaryStage.getHeight());

        Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
        if (!ButtonType.OK.equals(closeResponse.get())) {
            event.consume();
        } else {
            ExitApp app = new ExitApp();
            app.close();

        }
    };

}

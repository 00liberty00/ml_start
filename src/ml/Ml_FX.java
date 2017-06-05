/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ml.controller.CreateAdminController;
import ml.controller.CreateDBController;
import ml.controller.LoginController;
import ml.query.auth.AdminExists;
import ml.trial.TestTrial;
import ml.dialog.DialogAlert;
import ml.dialog.DialogTextInput;
import ml.exit.ExitApp;
import ml.ipmac.IpMac;
import ml.model.Comp;
import ml.query.created.Created;
import ml.query.trial.ChangeCreatedTrial;
import ml.query.trial.TrialInfo;

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

        IpMac ipMac = new IpMac();
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

        }

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("ML");
        File f = new File("src/hibernate.cfg.xml");
        if (f.exists()) {

            /*int l = 0;
            
            NewTrialClass ntc = new NewTrialClass();
            System.out.println("Дата создания! - " + ntc);
            if (l == 1) {
            
            }*/
            ExitApp app = new ExitApp();
            TestTrial testTrial = new TestTrial();
            if (testTrial.testDateTrial() == false && testTrial.falseTrial() == false) {

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
            } else {
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
            }
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
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                ExitApp app = new ExitApp();
                app.close();
            }
        });
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

}

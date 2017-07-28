/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import ml.dialog.DialogAlert;
import ml.model.UserSwing;
import ml.query.user.AdminUser;
import ml.query.user.PhoneUser;
import ml.query.user.UpdateAdmin;
import ml.xml.XMLSettings;
import ml.xml.model.Settings;

/**
 * Контроллер Настройки приложения
 *
 * @author Dave
 */
public class SettingsAppController implements Initializable {

    @FXML
    private ComboBox<BigDecimal> combo;
    @FXML
    private Button okButton;
    @FXML
    private TextField phoneTextField;
    @FXML
    private CheckBox smsCheck;

    private XMLSettings xmls = new XMLSettings();
    private Settings settings = new Settings();
    private BigDecimal rounding;
    private DialogAlert alert = new DialogAlert();
    private PhoneUser phoneUser = new PhoneUser();
    private UpdateAdmin updatePhoneUser = new UpdateAdmin();
    private AdminUser adminUser = new AdminUser();
    private UserSwing userSwing = new UserSwing();
    private boolean selectCheck = false;

    @FXML
    private void okAction(ActionEvent event) {

        roundingPrice();

        //если выбрана отправка смс
        if (selectCheck == true) {
            smsCheck();
        } else {
            settings.setSmsCheck(false);
        }

        xmls.newRecord(settings);

        alert.alert(null, null, "Настройки сохранены!");
    }

    //Обработка округления цены товара
    private Settings roundingPrice() {
        int i0 = combo.getValue().compareTo(new BigDecimal("100.00"));
        int i1 = combo.getValue().compareTo(new BigDecimal("100.10"));
        int i2 = combo.getValue().compareTo(new BigDecimal("100.11"));
        if (i0 == 0) {
            rounding = new BigDecimal("0");
        }
        if (i1 == 0) {
            rounding = new BigDecimal("1");
        }
        if (i2 == 0) {
            rounding = new BigDecimal("2");
        }
        settings.setRounding(rounding);
        return settings;
    }

    //Обработка смс информирования
    private Settings smsCheck() {
        //Возвращает значение админа
        adminUser.get();
        userSwing = adminUser.displayResult();

        boolean selectCheck = smsCheck.isSelected();
        settings.setSmsCheck(selectCheck);

        userSwing.setPhone(phoneTextField.getText());
        updatePhoneUser.update(userSwing);
        return settings;
    }

    //Выводит на экран выбранное раннее значение
    private void setComboValue() {

        BigDecimal defaultValue = xmls.getRounding();

        int i0 = defaultValue.compareTo(new BigDecimal("0"));
        int i1 = defaultValue.compareTo(new BigDecimal("1"));
        int i2 = defaultValue.compareTo(new BigDecimal("2"));
        if (i0 == 0) {
            combo.getSelectionModel().select(new BigDecimal("100.00"));

        }
        if (i1 == 0) {
            combo.getSelectionModel().select(new BigDecimal("100.10"));
        }
        if (i2 == 0) {
            combo.getSelectionModel().select(new BigDecimal("100.11"));

        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setComboValue();

        rounding = new BigDecimal("2");
        combo.getItems().add(new BigDecimal("100.00"));
        combo.getItems().add(new BigDecimal("100.10"));
        combo.getItems().add(new BigDecimal("100.11"));

        phoneTextField.setDisable(true);
        smsCheck.setOnAction((event) -> {
            selectCheck = smsCheck.isSelected();
            if (selectCheck == true) {
                phoneTextField.setDisable(false);
                phoneUser.phoneUser();
                phoneTextField.setText(phoneUser.displayResult().toString());

            } else {
                phoneTextField.setDisable(true);
            }
        });
    }
}

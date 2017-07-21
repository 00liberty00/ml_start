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
import javafx.scene.control.ComboBox;
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

    private XMLSettings xmls = new XMLSettings();
    private Settings settings = new Settings();
    private BigDecimal rounding;

    @FXML
    private void okAction(ActionEvent event) {

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

        xmls.newRounding(settings);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rounding = new BigDecimal("0");
        combo.getItems().add(new BigDecimal("100.00"));
        combo.getItems().add(new BigDecimal("100.10"));
        combo.getItems().add(new BigDecimal("100.11"));

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.window;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import ml.controller.CheckLayoutController;
import ml.modelLicense.Comp;

/**
 *
 * @author dave
 */
public class CheckWindow {

    

    public CheckWindow(Comp comp) {
        //Продажа товара
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/Check.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();
            CheckLayoutController checkLayoutController = loader.getController();
            checkLayoutController.setCompCard(comp);
            Stage stage = new Stage();
            stage.setTitle("Продажа товара");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

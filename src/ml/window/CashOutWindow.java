/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.window;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ml.controller.CashOutController;

/**
 * Окно вывода денег.
 *
 * @author dave
 */
public class CashOutWindow {

    public CashOutWindow() {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/CashOut.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Вывод денежных средств");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            //stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    public CashOutWindow(String sumOut, String note) {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/CashOut.fxml"));

            AnchorPane rootLayout = (AnchorPane) loader.load();
            CashOutController controller = (CashOutController) loader.getController();

            controller.setTopText(sumOut, note);

            Stage stage = new Stage();
            stage.setTitle("Вывод денежных средств");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            //stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

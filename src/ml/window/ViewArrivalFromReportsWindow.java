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
import ml.controller.ViewArrivalFromReportsController;

/**
 * Отчеты прихода после переход из отчета за день.
 *
 * @author dave
 */
public class ViewArrivalFromReportsWindow {

    public ViewArrivalFromReportsWindow() {

    }

    public void view(Long number) {
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/ViewArrivalFromReports.fxml"));
            BorderPane rootLayout = (BorderPane) loader.load();

            ViewArrivalFromReportsController controller = (ViewArrivalFromReportsController) loader.getController();

            controller.setData(number);

            Stage stage = new Stage();
            stage.setTitle("Отчеты прихода");
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

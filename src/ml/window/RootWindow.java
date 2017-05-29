/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.window;

import java.io.IOException;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ml.exit.ExitApp;

/**
 *
 * @author dave
 */
public class RootWindow {

    
    private ExitApp app = new ExitApp();
    
    public RootWindow() {
        //Продажа товара
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/RootLayout.fxml"));

            BorderPane rootLayout = (BorderPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle("ML 1.0");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    app.close();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

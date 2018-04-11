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
import ml.controller.RootLayoutController;
import ml.dialog.DialogChoose;
import ml.exit.ExitApp;
import ml.modelLicense.Comp;

/**
 *
 * @author dave
 */
public class RootWindow {
    
    private ExitApp app = new ExitApp();
    private Stage stage = new Stage();
    //private RootLayoutController rootLayoutController = new RootLayoutController();

    public RootWindow(Comp comp) {
        //Продажа товара
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/RootLayout.fxml"));
            
            BorderPane rootLayout = (BorderPane) loader.load();
            RootLayoutController rootLayoutController = loader.getController();
            rootLayoutController.setCompCard(comp);
            stage.setTitle("ML 1.0");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            stage.setMaximized(true);
            stage.setScene(scene);
            stage.show();

            //stage.setOnCloseRequest(confirmCloseEventHandler);
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    DialogChoose choose = new DialogChoose();
                    choose.alert("Выход из программы", null, "Вы уверены, что хотите выйти?");
                    
                    if (choose.display() == true) {
                        app.close();
                    } else {
                        event.consume();
                        
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

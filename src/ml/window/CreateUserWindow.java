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
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author dave
 */
public class CreateUserWindow {


    public CreateUserWindow() {
        //Продажа товара
        try {

            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ml/view/CreateUser.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Stage stage = new Stage();
            stage.setTitle("Новый пользователь");
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            //scene.getStylesheets().add("/styles/Styles.css");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

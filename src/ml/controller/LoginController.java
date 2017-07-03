/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ml.Ml_FX;
import ml.authentication.AuthenticationManag;
import ml.model.UserSwing;
import ml.modelLicense.License;
import ml.query.license.AddUserLicense;
import ml.query.user.AuthUser;
import ml.trial.TestTrial;
import ml.window.RootWindow;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * FXML Controller class
 *
 * @author dave
 */
public class LoginController implements Initializable {

    @FXML
    private Label loginLabel;
    @FXML
    private TextField loginTextField;
    @FXML
    private Label authLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private PasswordField passwordTextField;
    @FXML
    private Button okButton;
    @FXML
    private Label message;

    private final ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder();
    private static final AuthenticationManager am = new AuthenticationManag();
    private final AuthUser authUser = new AuthUser();
    private Stage dialogStage;
    private List res;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void okClicked(ActionEvent event) {
        enterInApp();
    }

    @FXML
    public void onEnter(ActionEvent ae) {
        enterInApp();
    }

    /**
     * Вход в приложение.
     */
    private void enterInApp() {
        okButton.setDisable(true);
        String user = loginTextField.getText();
        String pass = passwordTextField.getText();
        authUser.authenticationUser(user);
        res = authUser.displayResult();
        result(res, pass, user);
    }

    /**
     * Проверка на наличии связки логина и пароля.
     *
     * @param resultList
     * @param pass
     * @param user
     */
    private void result(List resultList, String pass, String user) {

        Ml_FX ml = new Ml_FX();

        TestTrial testTrial = new TestTrial();
        // Created c = new Created();
        AddUserLicense addUserLicense = new AddUserLicense();
        License license = testTrial.license();

        if (!resultList.isEmpty()) {
            for (Object o : resultList) {
                UserSwing u = (UserSwing) o;

                if (shaPasswordEncoder.isPasswordValid(u.getPassword(), pass, null)) {
                    Authentication request = new UsernamePasswordAuthenticationToken(user, pass);
                    Authentication result = am.authenticate(request);
                    SecurityContextHolder.getContext().setAuthentication(result);
                    //Открыть окно чека
                    try {
                        // +1 пользователь(ПК) в БД в поле ключа 
                        //c.addUser(license);
                        
                        if (license.getCountPc() > license.getIncludeUser()) {
                            addUserLicense.update(license);
                            new RootWindow();
                            //Закрыть окно авторизации
                            dialogStage.close();
                        } else {
                            message.setText("Лимит подключений исчерпан ");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    message.setText("Пользователя с таким логином \n" + "или паролем не существует");
                    okButton.setDisable(false);
                }
            }
        } else {
            message.setText("Пользователя с таким логином \n" + "или паролем не существует");
            okButton.setDisable(false);
        }

    }

}

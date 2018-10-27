/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ml.Ml_FX;
import ml.model.Check;
import ml.model.CheckDiscount;
import ml.model.CheckList;
import ml.model.CheckListNewPrice;
import ml.model.Discount;
import ml.print.Print;
import ml.query.check.AddCheck;
import ml.query.check.AddCheckList;
import ml.query.check.LastCheck;
import ml.query.checkDiscount.AddCheckDiscount;
import ml.query.checkNewPrice.AddCheckNewPrice;
import ml.query.discount.UpdateSumDiscount;
import ml.query.goods.UpdateResidueGood;
import ml.table.CheckTable;
import ml.util.MoneyRegexp;
import ml.util.ValidTextField;
import org.springframework.transaction.annotation.Transactional;

/**
 * FXML Controller class
 *
 * @author dave
 */
public class EndCheckController implements Initializable {

    @FXML
    private Label numLabel;
    @FXML
    private Label numberCheckLabel;
    @FXML
    private Label sumLabel;
    @FXML
    private TextField sumCheckTextField;
    @FXML
    private Label discLabel;
    @FXML
    private TextField discountCheckTextField;
    @FXML
    private Label cashLabel;
    @FXML
    private TextField cashCheckTextField;
    @FXML
    private Label shortChangeLabel;
    @FXML
    private TextField shortChangeCheckTextField;
    @FXML
    private Button cancelCheckButton;
    @FXML
    private Button okCheckButton;
    @FXML
    private Label message;

    private TableView<CheckTable> tableCheck;
    private Stage dialogStage;
    private List<Check> checkArrayList = new ArrayList<Check>();
    private List<CheckList> checkListArrayList = new ArrayList<CheckList>();
    private boolean inetConnect;
    private Check check = new Check();
    private CheckList checkList = new CheckList();
    private Discount discount = new Discount();
    private boolean okClicked = false;
    private AddCheckList addCheckList = new AddCheckList();
    private AddCheck addCheck = new AddCheck();
    private LastCheck lastCheck = new LastCheck();
    private ValidTextField validTextField = new ValidTextField();
    private UpdateSumDiscount dis = new UpdateSumDiscount();
    private boolean checkNewPrice = false;
    private BigDecimal newPrice = new BigDecimal(0.00);

    public boolean displayOkClicked() {

        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setSumText(String convertSum, String convertSumWithDiscount) {
        // set text from another class
        sumCheckTextField.setText(convertSum);
        discountCheckTextField.setText(convertSumWithDiscount);
    }

    public void setAddCheck(TableView<CheckTable> tableCheck, List<Check> check, List<CheckList> checkList, Discount discount, boolean checkNewPrice, BigDecimal newPrice) {
        // set text from another class
        this.tableCheck = tableCheck;
        this.checkArrayList = check;
        this.checkListArrayList = checkList;
        this.discount = discount;
        this.checkNewPrice = checkNewPrice;
        this.newPrice = newPrice;
    }

    @FXML
    private void getShortChange(KeyEvent event) {
        if ("0".equals(discountCheckTextField.getText())) {

            BigDecimal sumCheck = new BigDecimal(sumCheckTextField.getText());
            BigDecimal cashCheck = new BigDecimal(0);
            if (!"".equals(cashCheckTextField.getText())) {
                cashCheck = new BigDecimal(cashCheckTextField.getText());
            }
            shortChangeCheckTextField.setText(cashCheck.subtract(sumCheck).toString());
        } else {
            BigDecimal sumWithDiscountCheck = new BigDecimal(discountCheckTextField.getText());
            BigDecimal cashCheck = new BigDecimal(0);
            if (!"".equals(cashCheckTextField.getText())) {
                cashCheck = new BigDecimal(cashCheckTextField.getText());
            }
            shortChangeCheckTextField.setText(cashCheck.subtract(sumWithDiscountCheck).toString());
        }
    }

    @FXML
    private boolean isCancelClicked(ActionEvent event) {

        okClicked = false;
        dialogStage.close();
        return okClicked;
    }

    @Transactional
    @FXML
    private boolean isOkClicked(ActionEvent event) {
        //PrinterCheck printerCheck = new PrinterCheck();
        //Проверка соединения с интернетом
        //printerCheck.startPrint();
        try {
            FXMLLoader loaderPrintCheck = new FXMLLoader();
            loaderPrintCheck.setLocation(Ml_FX.class.getResource("/ml/view/PrintCheck.fxml"));
// Create the dialog Print.
            Stage dialogStagePrint = new Stage();
            dialogStagePrint.setTitle("Печать чека");
            dialogStagePrint.initModality(Modality.WINDOW_MODAL);
            dialogStagePrint.initOwner(dialogStage);
            AnchorPane pagePrint = (AnchorPane) loaderPrintCheck.load();
            Scene scenePrint = new Scene(pagePrint);
            dialogStagePrint.setScene(scenePrint);

            PrintCheckController controllerPrint = loaderPrintCheck.getController();
            //grid.add(first, 1, 1);
            //grid.add(second, 2, 2);

            Print p = new Print();
            MoneyRegexp moneyRegexp = new MoneyRegexp();
            UpdateResidueGood updateResidueGood = new UpdateResidueGood();
            boolean b = moneyRegexp.check(cashCheckTextField.getText());
            if (b == false) {
                message.setText("Введите сумму!!!");
            } else {

                for (Check check1 : checkArrayList) {
                    check = check1;
                    //addC.addCheck(check);
                    //check.setSum(sum);

                    BigDecimal money = new BigDecimal(cashCheckTextField.getText());
                    check.setMoney(money);
                    addCheck.add(check);

                    //printerCheck.printer(check.getName(), check.getAmount(), check.getPrice());
                }

                //Если есть дисконт, то запись
                if (discount.getNumcard() != null) {

                    AddCheckDiscount addCheckDiscount = new AddCheckDiscount();
                    CheckDiscount cd = new CheckDiscount();
                    UpdateSumDiscount updateSumDiscount = new UpdateSumDiscount();
                    BigDecimal sumDiscount = new BigDecimal(discountCheckTextField.getText()).setScale(2, RoundingMode.HALF_UP);

                    cd.setCheck(check);
                    cd.setDiscount(discount);
                    updateSumDiscount.update(discount, sumDiscount);
                    addCheckDiscount.add(cd);

                }

                lastCheck.get();                    //метод для номера последней записи в Check (Последний номер чека)
                for (CheckList check1 : checkListArrayList) {
                    checkList = check1;
                    checkList.setCheck(lastCheck.displayResult());
                    check.getCheckLists().add(checkList);
                    addCheckList.add(checkList);
                    updateResidueGood.update(checkList, null, null, false);
                    //printerCheck.printer(check.getName(), check.getAmount(), check.getPrice());

                    if (checkList.getNewPrice() == true) {
                        CheckListNewPrice checkListNewPrice = new CheckListNewPrice();
                        AddCheckNewPrice addCheckNewPrice = new AddCheckNewPrice();
                        checkListNewPrice.setCheckList(checkList);
                        checkListNewPrice.setNewPrice(newPrice);
                        //newPrice
                        addCheckNewPrice.add(checkListNewPrice);

                    }

                }

                //p.pageSetup(grid, dialogStage);
                //printerCheck.finishPrint(check.getNumber() + 1, sumCheckTextField.getText(),
                //discountCheckTextField.getText(), percent.toString());
                okClicked = true;
                dialogStage.close();

                controllerPrint.setPrintCheck(checkListArrayList, checkArrayList, discount,
                        new BigDecimal(sumCheckTextField.getText()).setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal(discountCheckTextField.getText()).setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal(cashCheckTextField.getText()).setScale(2, RoundingMode.HALF_UP),
                        new BigDecimal(shortChangeCheckTextField.getText()).setScale(2, RoundingMode.HALF_UP));
                controllerPrint.setDialogStage(dialogStagePrint);
                dialogStagePrint.show();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return okClicked;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Ввод в поле только цифры и точку
        cashCheckTextField.addEventFilter(KeyEvent.KEY_TYPED, validTextField.numeric_Validation(10));

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                cashCheckTextField.requestFocus();
                //cashCheckTextField.setStyle("-fx-text-inner-color: red;");
                //Включение диалоговых окон по нажатию клавиш
                cashCheckTextField.setOnKeyPressed(
                        event -> {
                            switch (event.getCode()) {
                                case ESCAPE:
                                    dialogStage.close();
                                    break;
                                case ENTER:
                                    //isOkClicked();
                                    break;
                            }
                        });
            }
        });
    }
}

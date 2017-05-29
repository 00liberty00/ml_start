/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import ml.model.Check;
import ml.model.CheckList;
import ml.model.Discount;
import ml.print.Print;
import ml.xml.XMLPrinter;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class PrintCheckController implements Initializable {

    @FXML
    private GridPane checkPane;

    private Stage dialogStage;
    private boolean okClicked = false;
    private List<Check> checkArrayList = new ArrayList<Check>();
    private List<CheckList> checkListArrayList = new ArrayList<CheckList>();
    private Print p = new Print();
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    private final Date date = new Date();
    private final String nowDate = dateFormat.format(date);
    private XMLPrinter xmlPrinter = new XMLPrinter();

    public boolean displayOkClicked() {

        return okClicked;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /*
    public void setSumText(String convertSum, String convertSumWithDiscount) {
    // set text from another class
    System.out.println("Получаем мы: " + convertSum);
    sumCheckTextField.setText(convertSum);
    discountCheckTextField.setText(convertSumWithDiscount);
    }*/
    public void setPrintCheck(List<CheckList> checkListArrayList, List<Check> checkArrayList,
            Discount discount, BigDecimal sumChecktText, BigDecimal discountText,
            BigDecimal cash, BigDecimal shortChange) {
        // set text from another class

        Label headerLabel = new Label(xmlPrinter.getHeaderPrinter());
        Label adressLabel = new Label(xmlPrinter.getAdressPrinter());
        Label infoLabel = new Label(xmlPrinter.getInfoPrinter());
        Label underline1Label = new Label("________________");
        Label underline2Label = new Label("________________");
        Label underline3Label = new Label("________________");

        Label footerLabel = new Label(xmlPrinter.getFooterPrinter());
        Label numberCheckLabel = new Label();
        Label sumLabel = new Label();
        Label sumCheckLabel = new Label();
        Label discountLabel = new Label();
        Label discountCheckLabel = new Label();
        Label discountCheckTextLabel = new Label();
        Label cashLabel = new Label();
        Label cashText = new Label();
        Label shortChangeLabel = new Label();
        Label shortChangeText = new Label();
        Label dateTextLabel = new Label();
        Label casier = new Label();
        int i = 0;

        headerLabel.setFont(new Font("Arial", 8));
        checkPane.add(headerLabel, 0, i);

        adressLabel.setFont(new Font("Arial", 8));
        checkPane.add(adressLabel, 0, ++i);

        infoLabel.setFont(new Font("Arial", 8));
        checkPane.add(infoLabel, 0, ++i);

        casier.setText("Кассир: " + checkArrayList.get(0).getUserSwing().getName());
        casier.setFont(new Font("Arial", 8));
        checkPane.add(casier, 0, ++i);

        underline1Label.setFont(new Font("Arial", 8));
        checkPane.add(underline1Label, 0, ++i);

        for (CheckList check1 : checkListArrayList) {

            Label name = new Label();
            Label aboutBuy = new Label();
            Label price = new Label();
            Label sum = new Label();

            i = ++i;

            name.setText(check1.getGoods().getName());
            name.setFont(new Font("Arial", 8));
            aboutBuy.setText(check1.getAmount().toString() + "  x  "
                    + check1.getGoods().getPrice().toString() + "  =  "
                    + check1.getAmount().multiply(check1.getGoods().getPrice()).setScale(2, RoundingMode.HALF_UP).toString());
            aboutBuy.setFont(new Font("Arial", 8));
            checkPane.setHalignment(aboutBuy, HPos.RIGHT);
            checkPane.add(name, 0, i);
            checkPane.add(aboutBuy, 0, ++i);

        }

        underline2Label.setFont(new Font("Arial", 8));
        checkPane.add(underline2Label, 0, ++i);

        //Если есть дисконт, то
        if (discount.getNumcard() != null) {
            discountLabel.setText("Скидка " + discount.getPercent() + "% : " + sumChecktText.subtract(discountText));
            discountLabel.setFont(new Font("Arial", 8));
            checkPane.add(discountLabel, 0, ++i);

            sumLabel.setText("Сумма чека: ");
            sumLabel.setFont(Font.font("Arial", 8));
            checkPane.add(sumLabel, 0, ++i);

            sumChecktText.setScale(2, RoundingMode.UP);
            sumCheckLabel.setText(sumChecktText.toString());
            sumCheckLabel.setFont(Font.font("Arial", 8));
            checkPane.setHalignment(sumCheckLabel, HPos.RIGHT);
            checkPane.add(sumCheckLabel, 0, i);

            discountCheckLabel.setText("Cумма со скидкой: ");
            discountCheckLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            //checkPane.setHalignment(discountCheckLabel, HPos.RIGHT);
            checkPane.add(discountCheckLabel, 0, ++i);

            discountText.setScale(2, RoundingMode.UP);
            discountCheckTextLabel.setText(discountText.toString());
            discountCheckTextLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            checkPane.setHalignment(discountCheckTextLabel, HPos.RIGHT);
            checkPane.add(discountCheckTextLabel, 0, i);

        } else {
            sumLabel.setText("Сумма чека: ");
            sumLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            checkPane.add(sumLabel, 0, ++i);

            sumCheckLabel.setText(sumChecktText.toString());
            sumCheckLabel.setFont(Font.font("Arial", FontWeight.BOLD, 10));
            checkPane.setHalignment(sumCheckLabel, HPos.RIGHT);
            checkPane.add(sumCheckLabel, 0, i);
        }

        cashLabel.setText("Наличными: ");
        cashLabel.setFont(new Font("Arial", 8));
        checkPane.add(cashLabel, 0, ++i);

        cash.setScale(2, RoundingMode.UP);
        cashText.setText(cash.toString());
        cashText.setFont(Font.font("Arial", 8));
        checkPane.setHalignment(cashText, HPos.RIGHT);
        checkPane.add(cashText, 0, i);

        shortChangeLabel.setText("Сдача: ");
        shortChangeLabel.setFont(new Font("Arial", 8));
        checkPane.add(shortChangeLabel, 0, ++i);

        shortChange.setScale(2, RoundingMode.UP);
        shortChangeText.setText(shortChange.toString());
        shortChangeText.setFont(Font.font("Arial", 8));
        checkPane.setHalignment(shortChangeText, HPos.RIGHT);
        checkPane.add(shortChangeText, 0, i);

        underline3Label.setFont(new Font("Arial", 8));
        checkPane.add(underline3Label, 0, ++i);

        dateTextLabel.setText(nowDate);
        dateTextLabel.setFont(Font.font("Arial", FontWeight.BOLD, 8));
        checkPane.setHalignment(dateTextLabel, HPos.RIGHT);
        checkPane.add(dateTextLabel, 0, ++i);

        numberCheckLabel.setText("Чека №: " + checkArrayList.get(0).getIdCheck());
        numberCheckLabel.setFont(new Font("Arial", 8));
        checkPane.add(numberCheckLabel, 0, ++i);

        footerLabel.setFont(new Font("Arial", 8));
        checkPane.setHalignment(footerLabel, HPos.CENTER);
        checkPane.add(footerLabel, 0, ++i);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                p.pageSetup(checkPane, dialogStage);

                if (p.getPrint() == true) {
                    dialogStage.close();
                }
            }
        });

    }

}

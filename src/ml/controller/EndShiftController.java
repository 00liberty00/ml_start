/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ml.mail.NewMail;
import ml.model.CaseRecord;
import ml.model.CashOut;
import ml.model.CheckList;
import ml.model.Total;
import ml.model.UserSwing;
import ml.query.caserecord.AddCaseRecord;
import ml.query.caserecord.DateCaseRecord;
import ml.query.cashout.AddCashOut;
import ml.query.check.Proceeds;
import ml.query.check.ProfitCheck;
import ml.query.goods.SumGoods;
import ml.query.total.AddTotal;
import ml.query.user.IdUserByName;
import ml.query.user.MailUser;
import ml.query.user.PhoneUser;
import ml.sms.Smsc;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.apache.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author Dave
 */
public class EndShiftController implements Initializable {

    @FXML
    private DatePicker datePicker = new DatePicker();
    @FXML
    private TextField sumCash;
    @FXML
    private TextField proceedsText;
    @FXML
    private Button ok;
    @FXML
    private Label info;

    // Инициализация логера
    private static final Logger log = Logger.getLogger(EndShiftController.class);
    private List<CaseRecord> crList;
    private Proceeds pr;
    private Total total = new Total();
    private List<CheckList> checkList;
    private BigDecimal endSum = new BigDecimal("0.00");                 //Сумма денег в конце дня
    private BigDecimal sumIn;                                           //Сумма введенных денег за день
    private BigDecimal sumOut;                                          //Сумма выведенных денег за день
    private BigDecimal proceeds;                                        //Выручка по живой кассе
    private BigDecimal proceedsCheck = new BigDecimal("0.00");          //Выручка по чекам
    private BigDecimal spareMoney;                                      //Сумма лишних денег
    private BigDecimal sumGoods = new BigDecimal("0.00");               //Сумма денег в товаре
    private BigDecimal profit;                                          //Сумма прибыли
    private MailUser mu = new MailUser();
    private PhoneUser pu = new PhoneUser();

    @FXML
    private void getDate() {

        //getSumCash();
    }

    @FXML
    private void getSumCash() {
        CaseRecord cr = null;
        sumIn = new BigDecimal("0.00");
        proceeds = new BigDecimal("0.00");
        sumOut = new BigDecimal("0.00");
        DateCaseRecord dateCaseRecord = new DateCaseRecord();
        dateCaseRecord.setDate(datePicker.getValue().toString());
        crList = dateCaseRecord.displayResult();
        if (!"".equals(sumCash.getText())) {
            endSum = new BigDecimal(sumCash.getText());
        }

        for (CaseRecord gg1 : crList) {
            cr = gg1;
            if (cr != null) {
                if (cr.getCashIn() != null) {
                    BigDecimal b = cr.getCashIn().getSumCash();
                    sumIn = sumIn.add(b);
                }
                if (cr.getCashOut() != null) {
                    BigDecimal b = cr.getCashOut().getSumCash();
                    sumOut = sumOut.add(b);
                }
            }
        }
        proceeds = endSum.add(sumOut).subtract(sumIn);
        proceedsText.setText(proceeds.toString());
        ok.setDisable(false);
        info.setVisible(false);
    }

    @FXML
    private void ok(ActionEvent event) {
        AddCashOut addCashOut = new AddCashOut();
        CashOut сashOut = new CashOut();
        CaseRecord caseRecord = new CaseRecord();
        AddCaseRecord addCaseRecord = new AddCaseRecord();

        spareMoney = new BigDecimal("0.00");
        profit = new BigDecimal("0.00");
        pr = new Proceeds();
        Smsc smsc = new Smsc();
        SumGoods sg = new SumGoods();
        AddTotal addTotal = new AddTotal();
        ProfitCheck profitCheck = new ProfitCheck();
        UserSwing userSwing = new UserSwing();
        IdUserByName idUserByName = new IdUserByName();
        Authentication authentication = SecurityContextHolder.getContext().
                getAuthentication();
        String login = authentication.getName();
        idUserByName.setLoginUser(login);   //метод для idUser по логину
        userSwing = idUserByName.displayResult(); //Возвращает пользователя

        LocalDate localDate = datePicker.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);

        //CaseRecord cr = new CaseRecord();
        //Сумма чеков(Выручка по чекам)
        //Возвращает выручку по дате
        pr.setDate(datePicker.getValue());
        proceedsCheck = pr.getProceeds();

        //Расчет лишних денег
        spareMoney = proceeds.subtract(proceedsCheck);

        //Расчет денег в товаре
        sg.sumGoods();
        sumGoods = sg.getSumGoods();

        //Расчет прибыли
        checkList = profitCheck.listCheckForProfit(localDate);
        CheckList check = null;
        for (CheckList c1 : checkList) {
            //check = c1;

            profit = profit.add(c1.getProfit());

        }
        //Запись в CashOut
        сashOut.setSumCash(new BigDecimal(sumCash.getText()));
        сashOut.setNote("Конец смены");
        addCashOut.add(сashOut);

        //Запись в CaseRecord
        caseRecord.setDate(date);
        сashOut.getCaseRecord().add(caseRecord);
        caseRecord.setCashOut(сashOut);
        caseRecord.setUserSwing(userSwing);
        addCaseRecord.add(caseRecord);

        //Запись в Total
        total.setSumGoods(sumGoods);
        total.setProfit(profit);
        total.setSpare(spareMoney);
        total.setDate(date);
        total.setUserSwing(userSwing);
        addTotal.add(total);

        /*        System.out.println("Сумма ввода денег : " + sumIn);
        System.out.println("Сумма вывода денег : " + sumOut);
        System.out.println("Сумма денег в конце дня : " + endSum);
        System.out.println("Выручка по живой кассе : " + proceeds);
        System.out.println("Выручка по чекам : " + proceedsCheck);
        System.out.println("Лишние деньги : " + spareMoney);
        System.out.println("Сумма денег в товаре : " + sumGoods);
        System.out.println("Прибыль : " + profit);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");*/

        sumCash.setText("0.00");
        proceedsText.setText("0.00");

        String messageSms = "Сумма в конце дня: " + endSum + "\n"
                + "Выручка: " + proceeds + "\n"
                + "Лишние деньги: " + spareMoney;

        String messageEmail = "Сумма служебного внесения: " + sumIn + "\n"
                + "Сумма вывода средств: " + sumOut + "\n"
                + "Сумма в конце дня: " + endSum + "\n"
                + "Выручка: " + proceeds + "\n"
                + "Лишние деньги: " + spareMoney + "\n"
                + "Прибыль: " + profit + "\n"
                + "Сумма в товаре: " + sumGoods + "\n"
                + "Сумма баланса для отправки смс: " + smsc.get_balance();

        //Отправка на почту
        mu.mail();
        String email = mu.displayResult().toString();
        NewMail mail = new NewMail();
        mail.main(email, messageEmail);

        //sms - оповещение
        pu.phoneUser();
        String phone = pu.displayResult().toString();
        smsc.send_sms(phone, messageSms, 1, "", "", 0, "kiosk", "");

        //После отправки на почту очищает файл log_file.log
        try {
            File sw = new File("src/ml/resources/log_file.log");
            PrintWriter writer;
            writer = new PrintWriter(sw);
            writer.print("");
            writer.close();
        } catch (FileNotFoundException ex) {
            java.util.logging.Logger.getLogger(EndShiftController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Закрывает окно
        Stage stage = (Stage) ok.getScene().getWindow();
        stage.close();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // логируем инфо
        log.info("Открыт 'Конец смены'");
        datePicker.setValue(LocalDate.now());
        ok.setDisable(true);

    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.trial;

import ml.query.trial.AddTrial;
import ml.query.trial.CountTrial;
import ml.model.Trial;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import ml.modelLicense.License;
import ml.query.license.LicenseCard;
import ml.query.trial.TrialInfo;

/**
 *
 * @author dave
 */
public class TestTrial {

    private final AddTrial addTrial = new AddTrial();
    private final CountTrial countTrial = new CountTrial();
    private boolean count;
    private final TrialInfo trialInfo = new TrialInfo();
    private License license = new License();
    private LicenseCard licenseCard = new LicenseCard();

    public TestTrial() {
        countTrial();
        firstDate();
    }

    //Проверка кол-во записей в Trial таблице
    private boolean countTrial() {
        countTrial.test();
        count = countTrial.displayResult();

        return count;
    }

    //Если запись в таблице не существет и не больше одной, то создать запись
    private void firstDate() {
        if (count == false) {
            Calendar date1 = Calendar.getInstance();
            Calendar date2 = Calendar.getInstance();
            date2.add(Calendar.MONTH, 1);    //Добавляет к текущей дате 1 месяц

            Trial trial = new Trial();
            trial.setIdTrial(1);
            trial.setLastdate(new Timestamp(date2.getTimeInMillis()));
            trial.setFirstdate(new Timestamp(date1.getTimeInMillis()));
            trial.setCreated(false);
            addTrial.add(trial);
        }
    }

    public boolean testDateTrial() {
        //Сравнение даты сегодняшней с датой окончания Trial-version
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar date1 = Calendar.getInstance();
        boolean b = false;
        try {

            String d = trialInfo.getTrial().getLastdate().toString();
            Date date = formatter.parse(d);
            Timestamp timestamp = new java.sql.Timestamp(date.getTime());
            Timestamp nowDate = new Timestamp(date1.getTimeInMillis());
            if (nowDate.before(timestamp)) {
                b = true;
            }

        } catch (ParseException pe) {
            pe.printStackTrace();
        }

        return b;
    }

    //Если триал версия, то показывает дату до окончания
    public String viewTrial() {
        String d = null;
        if (trialInfo.getTrial().isCreated() == false) {
            d = "Триал-версия до " + trialInfo.getTrial().getLastdate().toString();
        } else {
            d = "Полная версия! Номер лицензии: " + trialInfo.getTrial().getLicense();
        }
        return d;
    }

    //Если есть лицензия, то дает TRUE
    public boolean falseTrial() {
        boolean b;
        b = trialInfo.getTrial().isCreated() != false; //Триал-версия
        return b;
    }

    //Выводит idTrial
    public Integer idTrial() {
        Integer id = Integer.parseInt(trialInfo.getTrial().getIdTrial().toString());
        return id;
    }

    //Выводит license
    public License license() {
        licenseCard.setNum(trialInfo.getTrial().getLicense());
        license = licenseCard.displayResult();
        return license;
    }
}

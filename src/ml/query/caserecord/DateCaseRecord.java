/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.caserecord;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.CaseRecord;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dave
 */
public class DateCaseRecord {

    private static final String QUERY_BASED_OF_GOODS = "from CaseRecord where date like '";
    private List resultList;

    //Метод запроса отчетов по дням по дате
    public void setDate(LocalDate date) {
        //executeHQLQuery(QUERY_BASED_OF_GOODS + date + "%'");
        totalSalary(date);
    }

    //Добавить  00:00:00
    private Date addStartDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();

    }

    //Добавить  23:59:59
    private Date addEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();

    }

    private void totalSalary(LocalDate date) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(CaseRecord.class);

        Date dateStart = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //больше или равно 
        criteria.add(Restrictions.ge("date", addStartDay(dateStart)));

        //меньше чем
        criteria.add(Restrictions.lt("date", addEndDay(dateStart)));

        //criteria.setProjection(Projections.sum("sum"));
        resultList = criteria.list();
        displayResult();

        transaction.commit();
    }

    //Вывод списка
    public List<CaseRecord> displayResult() {
        return resultList;
    }
}

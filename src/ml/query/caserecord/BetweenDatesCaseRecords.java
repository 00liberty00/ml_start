/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.caserecord;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.CaseRecord;
import ml.model.Total;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Список чеков по дате
 *
 * @author Dave
 */
public class BetweenDatesCaseRecords {

    //from Check where date between '2017-12-14 00:00:00' and '2017-12-15 23:59:59' 
    private static final String QUERY_BASED_OF_GOODS = "from CaseRecord where date between '";
    private List resultList;
    private List resultListPlus;

    //Метод запроса списка чеков по дням
    public void setDate(String dateFrom, String dateTo) {
        executeHQLQuery(QUERY_BASED_OF_GOODS + dateFrom + " 00:00:00' and '" + dateTo + " 23:59:59'");
    }

    //Метод запроса списка прихода по дням
    public void setDates(LocalDate dateFrom, LocalDate dateTo) {
        Date dateStart = Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //dateTo = dateTo + " 00:00:00";
        executeHQLQueryX(addStartDay(dateStart), addEndDay(dateEnd));
        //executeHQLQuerySumCheck(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
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

    /* 
    //Добавить  текущее время
    private Date addEndDay(Date date) {
    Calendar cal = Calendar.getInstance();
    Calendar calTimeNow = Calendar.getInstance();
    
    cal.setTime(date);
    cal.set(Calendar.HOUR_OF_DAY, calTimeNow.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, calTimeNow.get(Calendar.MINUTE));
    cal.set(Calendar.SECOND, calTimeNow.get(Calendar.SECOND));
    return cal.getTime();
    }*/
    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            resultList = q.list();
            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //HQL-запрос
    private void executeHQLQueryX(Date dateFrom, Date dateTo) {
        try {
            Session session = HibernateUtil.openSession();
            Transaction transaction = null;
            transaction = session.beginTransaction();
            Criteria crit = session.createCriteria(Total.class);

            crit.add(Restrictions.between("date", dateFrom, addEndDay(dateTo)));
            crit.setProjection(Projections.sum("profit"));

            resultListPlus = crit.list();
            displayResultX();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //Вывод списка
    public List<CaseRecord> displayResult() {
        return resultList;
    }

    //Вывод списка
    public List<BigDecimal> displayResultX() {
        return resultListPlus;
    }
}

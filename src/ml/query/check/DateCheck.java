/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.Check;
import ml.model.CheckList;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Список чеков по дате
 *
 * @author Dave
 */
public class DateCheck {

    private static final String QUERY_BASED_OF_GOODS = "from Check where date like '";
    private List<Check> resultList;
    private List<CheckList> resultCheckList;


    /*//Метод запроса списка чеков по дням
    public void setDate(String date) {
    executeHQLQuery(QUERY_BASED_OF_GOODS + date + "%'");
    }*/
    //Метод запроса списка чеков по дням
    public void setDate(LocalDate date) {
        Date dateStart = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //dateTo = dateTo + " 00:00:00";
        executeHQLQuery(addStartDay(dateStart), addEndDay(dateEnd));
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

    //Продажи по дате
    private void executeHQLQuery(Date dateFrom, Date dateTo) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        
        Criteria crit = session.createCriteria(Check.class);
        crit.add(Restrictions.between("date", dateFrom, dateTo));
        resultList = crit.list();
        displayResultPlus();

        Criteria critCheckList = session.createCriteria(CheckList.class);
        Criteria suppCrit = critCheckList.createCriteria("check");
        critCheckList.createCriteria("goods");
        suppCrit.add(Restrictions.between("date", dateFrom, dateTo));
        resultCheckList = suppCrit.list();

        transaction.commit();
    }

    /* //HQL-запрос
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
    }*/
    //Вывод списка
    public List<Check> displayResultPlus() {
        return resultList;
    }

    //Вывод списка
    public List<CheckList> displayResultCheckList() {
        return resultCheckList;
    }

    //Вывод списка
    public List<Check> displayResult() {
        return resultList;
    }
}

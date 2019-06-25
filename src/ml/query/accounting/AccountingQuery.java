/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import ml.model.Accounting;
import ml.util.HibernateUtil;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dave
 */
public class AccountingQuery {

    private static final String EMPTY = "from GoodsAccounting gb";
    private static final String LAST_DATE_ACCOUNTING = "select a.date from Accounting a order by a.idAccounting desc";
    private static final String SUM_CHECK_ACCOUNTING = "select sum(price*amount) from Check1 where date <= '";
    private static final String SUM_PROCEEDS_ACCOUNTING = "select sum(proceeds) from Total where date <= '";
    private boolean isEmpty;
    private Date resultDate;
    private Double resultSumCheck;
    private Double resultSumProceeds;
    Transaction tx = null;
    Session session = HibernateUtil.openSession();
    Session sessionFactory;

    //!!!!!
    public void inspectEmptyTable() {
        executeHQLTableIsEmpty(EMPTY);
    }
    
    public void addAccounting(Accounting a) {
        executeHQLQuery(a);
    }

    public void lastDateAccounting() {
        executeHQLQuerylastDate(LAST_DATE_ACCOUNTING);
    }

    public void sumCheckAccounting(String nowDate, String date) {
        executeHQLQuerySumCheck(SUM_CHECK_ACCOUNTING + nowDate + "' and date > '" + date + "'");
    }

    public void sumProceedsAccounting(String nowDate, String date) {
        executeHQLQuerySumProceeds(SUM_PROCEEDS_ACCOUNTING + nowDate + "' and date > '" + date + "'");
    }

    //HQL-запрос
    private void executeHQLQuery(Accounting a) {
        try {
            tx = session.beginTransaction();
            session.save(a);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //!!!!!
    //Проверка, есть ли записи в таблице GoodsAccounting
    private void executeHQLTableIsEmpty(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            org.hibernate.Query x = session.createQuery(hql);
            List results = x.list();
            //Если таблица GoodsAccounting пустая, то копировать
            isEmpty = results.isEmpty();

            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Предыдущая дата учета
    private void executeHQLQuerylastDate(String hql) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String firstDate = "2014-01-01 00:00:00";

        try {
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            q.setMaxResults(1);
            resultDate = (Date) q.uniqueResult();
            //goods = q.list();
            if (resultDate == null) {
                resultDate = formatter.parse(firstDate);;
            }
            displayResultDate();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //Сумма чеков от последн учета
    private void executeHQLQuerySumCheck(String hql) {
        try {
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            q.setMaxResults(1);
            resultSumCheck = Double.parseDouble(q.uniqueResult().toString());
            //goods = q.list();
            displayResultSumCheck();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //Сумма выручки по живой кассе от последн учета
    private void executeHQLQuerySumProceeds(String hql) {
        try {
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            q.setMaxResults(1);
            String s = "0.00";
            if (q.uniqueResult() == null){
                resultSumProceeds = Double.parseDouble(s);
            }
            else{
                s = q.uniqueResult().toString();
                resultSumProceeds = Double.parseDouble(s);
            }
            
            //goods = q.list();
            displayResultSumProceeds();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //Вывод списка
    public String displayResultDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sdf.format(resultDate);
    }

    //Вывод суммы чеков
    public Double displayResultSumCheck() {

        return new BigDecimal(resultSumCheck).setScale(2, RoundingMode.UP).doubleValue(); //Округление до 0,00
    }

    //Вывод суммы выручек
    public Double displayResultSumProceeds() {

        return new BigDecimal(resultSumProceeds).setScale(2, RoundingMode.UP).doubleValue(); //Округление до 0,00
    }
    
    //Вывод проверки записей в таблице
    public boolean displayInspectTable() {

        return isEmpty;
    }
}

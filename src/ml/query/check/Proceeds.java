/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.math.BigDecimal;
import java.time.LocalDate;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Выручка по кассе компьютера
 * @author Dave
 */
public class Proceeds {

    private static final String SUM_CHECK = "select sum(c.sum) from Check c where c.date >= '";
    private Object result;

    //Метод запроса Последнего чека
    public void setDate(LocalDate date) {
        executeHQLQuery(SUM_CHECK + date + " 00:00:00' and c.date < '" + date + " 23:59:59'");
    }

    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            result = q.uniqueResult();

            getProceeds();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    //Вывод списка
    public BigDecimal getProceeds() {
        if (result != null) {
            return new BigDecimal(result.toString());
        }
        else{
            return new BigDecimal("0.00");
        }
    }
}

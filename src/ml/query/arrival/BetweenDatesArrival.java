/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.arrival;

import java.util.List;
import ml.model.Arrival;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Список чеков по дате
 *
 * @author Dave
 */
public class BetweenDatesArrival {

    //from Check where date between '2017-12-14 00:00:00' and '2017-12-15 23:59:59' 
    private static final String QUERY_BASED_OF_GOODS = "from Arrival where date between '";
    private List resultList;

    //Метод запроса списка чеков по дням
    public void setDate(String dateFrom, String dateTo) {
        executeHQLQuery(QUERY_BASED_OF_GOODS + dateFrom + " 00:00:00' and '" + dateTo + " 23:59:59'");
    }

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

    //Вывод списка
    public List<Arrival> displayResult() {
        return resultList;
    }
}

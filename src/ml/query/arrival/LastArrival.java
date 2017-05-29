/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.arrival;

import ml.model.Arrival;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Вовзращает последний чек
 *
 * @author dave
 */
public class LastArrival {

    private Arrival arrival = new Arrival();

    public void get() {
        executeHQLQuery();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Arrival order by id_arrival desc");
            q.setMaxResults(1);
            arrival = (Arrival) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Arrival displayResult() {

        return arrival;
    }
}

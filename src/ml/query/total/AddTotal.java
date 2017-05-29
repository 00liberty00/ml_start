/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.total;

import ml.model.Total;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Add Total
 *
 * @author Dave
 */
public class AddTotal {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Total total) {
        executeHQLQuery(total);
    }

    //HQL-запрос
    private void executeHQLQuery(Total total) {
        try {
            tx = session.beginTransaction();
            session.save(total);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

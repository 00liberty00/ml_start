/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.trial;

import ml.util.HibernateUtil;
import ml.model.Trial;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dave
 */
public class AddTrial {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Trial trial) {
        executeHQLQuery(trial);
    }

    //HQL-запрос
    private void executeHQLQuery(Trial trial) {
        try {
            tx = session.beginTransaction();
            session.save(trial);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

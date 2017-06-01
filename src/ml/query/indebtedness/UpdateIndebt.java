/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.indebtedness;

import ml.model.Orders;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class UpdateIndebt {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(Orders o) {
        executeHQLQuery(o);
    }

    //HQL-запрос
    private void executeHQLQuery(Orders o) {
        try {
            tx = session.beginTransaction();
            //session.load(Orders.class, o.getIdOrder());
            session.update(o);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

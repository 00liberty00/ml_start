/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.orders;

import ml.model.Orders;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Создает запись в Долгах.
 *
 * @author dave
 */
public class AddOrders {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Orders o) {
        executeHQLQuery(o);
    }

    //HQL-запрос
    private void executeHQLQuery(Orders orders) {
        try {
            tx = session.beginTransaction();
            session.save(orders);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

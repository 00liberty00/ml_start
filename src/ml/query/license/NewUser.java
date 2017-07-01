/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.license;

import ml.modelLicense.User;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class NewUser {
    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtilLic.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(User user) {
        executeHQLQuery(user);
    }

    //HQL-запрос
    private void executeHQLQuery(User user) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(user);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

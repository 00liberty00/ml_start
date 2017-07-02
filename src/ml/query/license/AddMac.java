/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.license;

import ml.modelLicense.Comp;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class AddMac {
    Session session = HibernateUtilLic.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Comp comp) {
        executeHQLQuery(comp);
    }

    //HQL-запрос
    private void executeHQLQuery(Comp comp) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(comp);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

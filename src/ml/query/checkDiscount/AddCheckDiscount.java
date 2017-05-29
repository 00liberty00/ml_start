/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.checkDiscount;

import ml.model.CheckDiscount;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dave
 */
public class AddCheckDiscount {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(CheckDiscount checkDiscount) {
        executeHQLQuery(checkDiscount);
    }

    //HQL-запрос
    private void executeHQLQuery(CheckDiscount checkDiscount) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(checkDiscount);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

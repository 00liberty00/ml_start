/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import ml.model.Check;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает чек
 * @author dave
 */
public class AddCheck {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Check check) {
        executeHQLQuery(check);
    }

    //HQL-запрос
    private void executeHQLQuery(Check check) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(check);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

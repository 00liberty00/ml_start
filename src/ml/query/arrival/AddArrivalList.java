/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.arrival;

import ml.model.ArrivalList;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author dave
 */
public class AddArrivalList {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(ArrivalList arrivalList) {
        executeHQLQuery(arrivalList);
    }

    //HQL-запрос
    private void executeHQLQuery(ArrivalList arrivalList) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(arrivalList);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.checkNewPrice;

import ml.model.CheckListNewPrice;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает чек
 * @author dave
 */
public class AddCheckNewPrice {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(CheckListNewPrice checkListNewPrice) {
        executeHQLQuery(checkListNewPrice);
    }

    //HQL-запрос
    private void executeHQLQuery(CheckListNewPrice checkListNewPrice) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(checkListNewPrice);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

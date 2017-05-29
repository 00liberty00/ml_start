/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import ml.model.Goods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает новый товар
 * @author dave
 */
public class NewGood {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(Goods goods) {
        executeHQLQuery(goods);
    }

    //HQL-запрос
    private void executeHQLQuery(Goods goods) {
        try {
            tx = session.beginTransaction();
            session.save(goods);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

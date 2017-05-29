/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.categoryGood;

import ml.model.CategoryGoods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает чек
 * @author dave
 */
public class AddCategoryGood {

    //CalculationRemainderMinus remainderMinus = new CalculationRemainderMinus();
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(CategoryGoods categoryGood) {
        executeHQLQuery(categoryGood);
    }

    //HQL-запрос
    private void executeHQLQuery(CategoryGoods categoryGood) {
        try {
            tx = session.beginTransaction();
            //remainderMinus.idGoods(s, check.getAmount());
            session.save(categoryGood);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

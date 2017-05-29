/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.cashin;

import ml.model.CashIn;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает запись в Ввод денежных средств.
 * @author dave
 */
public class AddCashIn {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(CashIn сashIn) {
        executeHQLQuery(сashIn);
    }

    //HQL-запрос
    private void executeHQLQuery(CashIn сashIn) {
        try {
            tx = session.beginTransaction();
            session.save(сashIn);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

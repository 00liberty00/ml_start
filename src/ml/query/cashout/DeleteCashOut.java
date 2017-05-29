/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.cashout;

import ml.model.CashOut;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Создает запись в Вывод денежных средств.
 *
 * @author dave
 */
public class DeleteCashOut {
    
    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;
    
    public void delete(CashOut cashOut) {
        executeHQLQuery(cashOut);
    }

    //HQL-запрос
    private void executeHQLQuery(CashOut cashOut) {
        try {
            tx = session.beginTransaction();

//            Serializable id = cashOut.getIdCashOut();
//            Object persistentInstance = session.load(CashOut.class, id);
//            if (persistentInstance != null) {
//                session.delete(persistentInstance);
//            }
            session.delete(cashOut);
            //session.delete(cashOut);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

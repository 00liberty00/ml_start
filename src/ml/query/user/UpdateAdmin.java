/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.user;

import ml.model.UserSwing;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Обновление администратора
 * @author dave
 */
public class UpdateAdmin {

    private final Session session = HibernateUtil.openSession();
    Transaction tx = null;
    
    /**
     * обновление администратора.
     */
    public void update(UserSwing us){
        query(us);
    }
    
    /**
     * Выполнение запроса.
     */
    private void query(UserSwing us){
        try {
            tx = session.beginTransaction();
            session.update(us);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        finally {
            session.clear();
        }
        
    }
}

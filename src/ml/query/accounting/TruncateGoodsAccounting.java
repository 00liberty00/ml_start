/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class TruncateGoodsAccounting {
    private static final String TRUNCATE = "truncate goodsaccounting";
    
    public void truncateAccounting() {
        executeHQLQuery(TRUNCATE);
    }

    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query query = session.createSQLQuery(hql);
            query.executeUpdate();
            //session.createQuery(hql);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}

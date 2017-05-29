/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.user;

import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dave
 */
public class PhoneUser {
    private static final String PHONE = "select phone from UserSwing where login = 'admin'";
    private Object result;
    
    public void phoneUser() {
        executeHQLQuery(PHONE);
    }

    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            result = q.uniqueResult();
            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Вывод списка
    public Object displayResult(){
        return result;
    }
}

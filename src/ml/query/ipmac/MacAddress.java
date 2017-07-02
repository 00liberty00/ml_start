/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.ipmac;

import ml.model.Check;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Вовзращает последний чек
 *
 * @author dave
 */
public class MacAddress {

    private Check check = new Check();
    boolean checkMac = false;
    private final String QUERY = "select hex(mac) from comp";
    
    public void get() {
        executeHQLQuery(QUERY);
    }

    //HQL-запрос
    private void executeHQLQuery(String s) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query q = session.createQuery(s);
            q.setMaxResults(1);
            check = (Check) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    
    
    
    
    public boolean displayResult() {

        return checkMac;
    }
}

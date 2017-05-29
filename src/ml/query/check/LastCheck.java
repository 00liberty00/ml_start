/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

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
public class LastCheck {

    private Check check = new Check();

    public void get() {
        executeHQLQuery();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Check order by id_check desc");
            q.setMaxResults(1);
            check = (Check) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Check displayResult() {

        return check;
    }
}

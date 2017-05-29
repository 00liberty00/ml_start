/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.cancellation;

import ml.model.Writeoff;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Вовзращает последнее списание
 *
 * @author dave
 */
public class LastCancel {

    private Writeoff cancel = new Writeoff();

    public void get() {
        executeHQLQuery();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Writeoff order by id_writeoff desc");
            q.setMaxResults(1);
            cancel = (Writeoff) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Writeoff displayResult() {

        return cancel;
    }
}

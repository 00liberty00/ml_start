/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.barcode;

import ml.model.Barcode;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 * Вовзращает последний внутренний штрих код 
 *
 * @author dave
 */
public class LastBarcode {

    private Barcode barcode = new Barcode();

    public void get() {
        executeHQLQuery();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            Query q = session.createQuery("from Barcode order by id_barcode desc");
            q.setMaxResults(1);
            barcode = (Barcode) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Barcode displayResult() {

        return barcode;
    }
}

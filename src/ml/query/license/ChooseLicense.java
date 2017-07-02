/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.license;

import ml.modelLicense.License;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dave
 */
public class ChooseLicense {
     private License license = new License();

    public void get() {
        executeHQLQuery();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            Session session = HibernateUtilLic.openSession();
            session.beginTransaction();
            Query q = session.createQuery(" from License where created = 0 and countPC = 1");
            q.setMaxResults(1);
            license = (License) q.uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public License displayResult() {

        return license;
    }
}

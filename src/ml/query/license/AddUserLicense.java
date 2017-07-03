/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.license;

import ml.modelLicense.License;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class AddUserLicense {

    Session session = HibernateUtilLic.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(License license) {
        executeHQLQuery(license);
    }

    //HQL-запрос
    private void executeHQLQuery(License license) {
        try {
            tx = session.beginTransaction();
            session.load(License.class, license.getIdLicense());
            license.setIncludeUser(license.getIncludeUser() + 1);
            //discount.setName("David");
            session.update(license);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

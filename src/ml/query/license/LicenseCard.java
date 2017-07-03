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
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dave
 */
public class LicenseCard {
    private License license = new License();

    //Метод запроса по номеру карты
    public void setNum(String licenseNum) {
        executeHQLQuery(licenseNum);
    }

    //HQL-запрос
    private void executeHQLQuery(String licenseNum) {
        try {
            Session session = HibernateUtilLic.openSession();
            session.beginTransaction();

            license = (License) session.createCriteria(License.class).add(Restrictions.eq("license", Long.valueOf(licenseNum))).uniqueResult();

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

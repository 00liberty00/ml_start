/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.compCard;

import ml.modelLicense.Comp;
import ml.modelLicense.License;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dave
 */
public class CompCard {

    private Comp comp = new Comp();

    //Метод запроса по номеру карты
    public void setNum(String name, License license) {
        executeHQLQuery(name, license);
    }

    //HQL-запрос
    private void executeHQLQuery(String name, License license) {
        try {
            Session session = HibernateUtilLic.openSession();
            session.beginTransaction();

            comp = (Comp) session.createCriteria(Comp.class).add(Restrictions.eq("name", name)).add(Restrictions.eq("license", license)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Comp displayResult() {
        //Передача параметра в rootLayoutController для вывода сообщения
        return comp;
    }
}

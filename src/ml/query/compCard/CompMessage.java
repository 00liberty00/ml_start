/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.compCard;

import ml.modelLicense.Comp;
import ml.util.HibernateUtilLic;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Dave
 */
public class CompMessage {

    private Comp comp = new Comp();

    //Метод запроса по номеру карты
    public void setComp(Comp comp) {
        executeHQLQuery(comp);
    }

    //HQL-запрос
    private void executeHQLQuery(Comp comp) {
        try {
            Session session = HibernateUtilLic.openSession();
            session.beginTransaction();

            this.comp = (Comp) session.createCriteria(Comp.class).add(Restrictions.eq("idComp", comp.getIdComp())).uniqueResult();

            displayResult();
            session.getTransaction().commit();
            session.clear();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Comp displayResult() {
        //Передача параметра в rootLayoutController для вывода сообщения
        return comp;
    }
}

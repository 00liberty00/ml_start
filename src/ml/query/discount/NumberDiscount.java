/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.discount;

import ml.model.Discount;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает процент дисконтной карты по ее номеру
 *
 * @author dave
 */
public class NumberDiscount {

    private Discount discount = new Discount();

    //Метод запроса по номеру карты
    public void numberDiscount(String numberDiscount) {
        executeHQLQuery(numberDiscount);
    }

    //HQL-запрос
    private void executeHQLQuery(String numcard) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            discount = (Discount) session.createCriteria(Discount.class).add(Restrictions.eq("numcard", numcard)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Discount displayResult() {
        return discount;
    }
}

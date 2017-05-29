/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.discount;

import java.math.BigDecimal;
import ml.model.Discount;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Обновляет сумму дисконтной карты
 *
 * @author dave
 */
public class UpdateSumDiscount {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(Discount discount, BigDecimal sumDiscount) {
        executeHQLQuery(discount, sumDiscount);
    }

    //HQL-запрос
    private void executeHQLQuery(Discount discount, BigDecimal sumDiscount) {
        try {
            tx = session.beginTransaction();
            session.load(Discount.class, discount.getIdDiscount());
            BigDecimal bb = discount.getSumChecks().add(sumDiscount);
            discount.setSumChecks(bb);
            //discount.setName("David");
            session.update(discount);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

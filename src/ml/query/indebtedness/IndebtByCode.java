/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.indebtedness;

import ml.model.Orders;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class IndebtByCode {

    private Orders o = new Orders();

    //Метод запроса по коду товара
    public void setId(Integer id) {
        executeHQLQuery(id);
    }

    //HQL-запрос
    private void executeHQLQuery(Integer id) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            o = (Orders) session.createCriteria(Orders.class).add(Restrictions.eq("idOrder", id)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Orders displayResult() {
        return o;
    }
}

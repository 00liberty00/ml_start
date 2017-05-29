/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import ml.model.Goods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class GoodByCode {

    private Goods goods = new Goods();

    //Метод запроса по коду товара
    public void setCode(String code) {
        executeHQLQuery(code);
    }

    //HQL-запрос
    private void executeHQLQuery(String code) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            goods = (Goods) session.createCriteria(Goods.class).add(Restrictions.eq("code", code)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Goods displayResult() {
        return goods;
    }
}

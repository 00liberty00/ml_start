/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import ml.model.Goods;
import ml.model.GoodsAccounting;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class GoodAccountingByCode {

    private GoodsAccounting goodsAcc = new GoodsAccounting();

    //Метод запроса по коду товара
    public void setCode(Goods goods) {
        executeHQLQuery(goods);
    }

    //HQL-запрос
    private void executeHQLQuery(Goods goods) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            goodsAcc = (GoodsAccounting) session.createCriteria(GoodsAccounting.class).add(Restrictions.eq("goods", goods)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public GoodsAccounting displayResult() {
        return goodsAcc;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import java.math.BigDecimal;
import ml.model.GoodsAccounting;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class UpdateResidueGoodAccounting {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(GoodsAccounting ga, BigDecimal residueDiff) {
        executeHQLQuery(ga, residueDiff);
    }

    //HQL-запрос
    private void executeHQLQuery(GoodsAccounting ga, BigDecimal residueDiff) {
        try {
            tx = session.beginTransaction();
            GoodsAccounting goodsAcc = new GoodsAccounting();
            BigDecimal bb = new BigDecimal(0.0);

            //Добавление +1

            goodsAcc = (GoodsAccounting) session.load(GoodsAccounting.class, ga.getGoods().getIdGoods());
            bb = goodsAcc.getResidueNew().add(new BigDecimal(1.00));

            goodsAcc.setResidueNew(bb);
            
            //обновление Разницы остатка в товаре
            goodsAcc.setResidueDiff(residueDiff);
            //discount.setName("David");
            session.update(goodsAcc);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

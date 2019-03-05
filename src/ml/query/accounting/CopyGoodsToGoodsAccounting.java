/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import java.util.List;
import ml.model.GoodsAccounting;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Dave
 */
public class CopyGoodsToGoodsAccounting {

    private Session sess;

    public void copyGoodsToGoodsAccounting() {
        executeHQLQuery();
    }

    /**
     * Список товара в goodsaccounting
     *
     * @return
     */
    private List<GoodsAccounting> listGoodsAccounting() {

        return sess.createQuery("from GoodsAccounting")
                .list();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            sess = HibernateUtil.openSession();
            sess.beginTransaction();
            //Если таблица GoodsBackup пустая, то копировать
            if (listGoodsAccounting().isEmpty()) {
                sess.createSQLQuery("insert into goodsaccounting select null, g.date, 0, 0, 0, g.id_goods from goods g")
                        .executeUpdate();
            }

            sess.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import java.util.List;
import ml.model.Goods;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 *  Список товара из таблицы для учета
 * @author Dave
 */
public class ListGoodsAccounting {

    private Session sess;

    public List<Goods> listGoodsAccounting() {
        sess = HibernateUtil.openSession();
        return sess.createQuery("select goods from GoodsAccounting")
                .list();
    }
}

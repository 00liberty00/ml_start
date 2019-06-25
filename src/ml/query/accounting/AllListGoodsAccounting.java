/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import java.util.List;
import ml.model.GoodsAccounting;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 *  Список товара из таблицы для учета
 * @author Dave
 */
public class AllListGoodsAccounting {

    private Session sess;

    public List<GoodsAccounting> allListGoodsForAccounting() {
        sess = HibernateUtil.openSession();
        return sess.createQuery("from GoodsAccounting")
                .list();
    }
}

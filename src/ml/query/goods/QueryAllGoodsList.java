/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import ml.model.Goods;
import ml.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class QueryAllGoodsList {

    private Session sess;

    @SuppressWarnings("unchecked")
    public List<Goods> listGoods() {
        sess = HibernateUtil.openSession();
        return sess.createQuery("from Goods order by code")
                .list();
    }

    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {
        sess = HibernateUtil.openSession();
        sess.clear();
    }
}

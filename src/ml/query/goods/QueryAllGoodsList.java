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

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<Goods> listGoods() {

        return sess.createQuery("from Goods")
                .list();
    }
    
    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {

        sess.clear();
    }
}

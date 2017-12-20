/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import ml.model.Goods;
import ml.util.HibernateUtil;
import java.util.List;
import ml.model.CategoryGoods;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class GoodsListByCategory {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<Goods> listGoods(CategoryGoods categoryGoods) {

                  //  goods = (Goods) session.createCriteria(Goods.class).add(Restrictions.eq("code", code)).uniqueResult();

        
        return sess.createQuery("from Goods where id_categoryGoods = " + categoryGoods.getIdCategoryGoods()).list();
    }
    
    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {

        sess.clear();
    }
}

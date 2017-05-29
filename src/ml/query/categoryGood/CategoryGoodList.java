/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.categoryGood;

import ml.util.HibernateUtil;
import java.util.List;
import ml.model.CategoryGoods;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class CategoryGoodList {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<CategoryGoods> getList() {

        return sess.createQuery("from CategoryGoods")
                .list();
    }
    
    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {

        sess.clear();
    }
}

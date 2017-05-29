/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.favorite;

import ml.util.HibernateUtil;
import java.util.List;
import ml.model.Favorite;
import org.hibernate.Session;

/**
 * Список категорий избранного товара
 * @author dave
 */
public class FavoriteList {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<Favorite> listFavoriteCategory() {

        return sess.createQuery("from Favorite")
                .list();
    }
    
    /*//Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {
    
    sess.clear();
    }*/
}

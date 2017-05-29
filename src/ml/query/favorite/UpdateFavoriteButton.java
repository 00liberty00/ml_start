/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.favorite;

import ml.model.Favorite;
import ml.model.Goods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Обновляет сумму дисконтной карты
 *
 * @author dave
 */
public class UpdateFavoriteButton {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(Favorite favorite, Goods goods) {
        executeHQLQuery(favorite, goods);
    }

    //HQL-запрос
    private void executeHQLQuery(Favorite favorite, Goods goods) {
        try {
            tx = session.beginTransaction();
            session.load(Favorite.class, favorite.getIdFavorite());
            favorite.setGoods(goods);
            //discount.setName("David");
            session.update(favorite);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

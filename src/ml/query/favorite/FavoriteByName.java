/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.favorite;

import ml.model.CategoryFavorite;
import ml.model.Favorite;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает Favorite
 *
 * @author dave
 */
public class FavoriteByName {

    private Favorite favorite = new Favorite();

    public void setFav(CategoryFavorite сategoryFavorite, String buttonName) {
        executeHQLQuery(сategoryFavorite, buttonName);
    }

    //HQL-запрос
    private void executeHQLQuery(CategoryFavorite сategoryFavorite, String buttonName) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
//Query query = session.createQuery(«from Bus where route_id = :routeId „).setLong(“routeId», route_id);
            favorite = (Favorite) session.createCriteria(Favorite.class).add(Restrictions.eq("categoryFavorite", сategoryFavorite)).add(Restrictions.eq("buttonName", buttonName)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public Favorite displayResult() {
        return favorite;
    }
}

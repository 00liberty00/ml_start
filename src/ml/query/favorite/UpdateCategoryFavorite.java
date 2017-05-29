/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.favorite;

import ml.model.CategoryFavorite;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Обновляет сумму дисконтной карты
 *
 * @author dave
 */
public class UpdateCategoryFavorite {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(CategoryFavorite сategoryFavorite, String name) {
        executeHQLQuery(сategoryFavorite, name);
    }

    //HQL-запрос
    private void executeHQLQuery(CategoryFavorite сategoryFavorite, String name) {
        try {
            tx = session.beginTransaction();
            session.load(CategoryFavorite.class, сategoryFavorite.getIdCategoryFavorite());
            сategoryFavorite.setName(name);
            //discount.setName("David");
            session.update(сategoryFavorite);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

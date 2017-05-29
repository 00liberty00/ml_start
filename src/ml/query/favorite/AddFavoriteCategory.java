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
 * Создание категории избранного
 * @author dave
 */
public class AddFavoriteCategory {

    private final Session session = HibernateUtil.openSession();
    Transaction tx = null;
    
    /**
     * Добавление категории избранного.
     */
    public void add(CategoryFavorite cat){
        query(cat);
    }
    
    /**
     * Выполнение запроса.
     */
    private void query(CategoryFavorite cat){
        try {
            tx = session.beginTransaction();
            session.save(cat);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        finally {
            session.clear();
        }
        
    }
}

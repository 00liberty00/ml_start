/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.favorite;

import ml.model.Favorite;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Создание кнопки избранного
 * @author dave
 */
public class AddFavoriteButton {

    private final Session session = HibernateUtil.openSession();
    Transaction tx = null;
    
    /**
     * Добавление кнопки избранного.
     * @param fav
     */
    public void add(Favorite fav){
        query(fav);
    }
    
    /**
     * Выполнение запроса.
     */
    private void query(Favorite fav){
        try {
            tx = session.beginTransaction();
            session.save(fav);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
        finally {
            session.clear();
        }
        
    }
}

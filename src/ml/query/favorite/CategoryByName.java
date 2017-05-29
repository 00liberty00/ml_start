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
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает процент дисконтной карты по ее номеру
 *
 * @author dave
 */
public class CategoryByName {

    private CategoryFavorite categoryFavorite = new CategoryFavorite();

    //Метод запроса по номеру карты
    public void setName(String name) {
        executeHQLQuery(name);
    }

    //HQL-запрос
    private void executeHQLQuery(String name) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            categoryFavorite = (CategoryFavorite) session.createCriteria(CategoryFavorite.class).add(Restrictions.eq("name", name)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public CategoryFavorite displayResult() {
        return categoryFavorite;
    }
}

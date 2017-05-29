/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.categoryGood;

import ml.model.CategoryGoods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает Категорию товара
 *
 * @author dave
 */
public class CategoryGoodsByName {

    private CategoryGoods categoryGoods = new CategoryGoods();

    //Метод запроса по названию категории
    public void setCode(String name) {
        executeHQLQuery(name);
    }

    //HQL-запрос
    private void executeHQLQuery(String name) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            categoryGoods = (CategoryGoods) session.createCriteria(CategoryGoods.class).add(Restrictions.eq("name", name)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public CategoryGoods displayResult() {
        return categoryGoods;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.util.List;
import ml.model.Check;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *  Список чеков по дате
 * @author Dave
 */
public class DateCheck {
    private static final String QUERY_BASED_OF_GOODS = "from Check where date like '";
    private List resultList;
    
    //Метод запроса списка чеков по дням
    public void setDate(String date) {
        executeHQLQuery(QUERY_BASED_OF_GOODS + date + "%'");
    }

    
    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            resultList = q.list();
            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Вывод списка
    public List<Check> displayResult(){
        return resultList;
    }
}

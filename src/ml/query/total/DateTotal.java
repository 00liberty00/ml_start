/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.total;

import ml.model.Total;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *  Выводит Total по дате
 * @author Dave
 */
public class DateTotal {
    private static final String QUERY = "from Total where date like '";
    private Object result;
    
    //Метод запроса отчетов по дням по дате
    public void setDate(String date) {
        executeHQLQuery(QUERY + date + "%'");
    }

    
    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            result = q.uniqueResult();
            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Вывод
    public Total displayResult(){
        return (Total) result;
    }
}

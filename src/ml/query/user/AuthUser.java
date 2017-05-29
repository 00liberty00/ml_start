/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.user;

import ml.util.HibernateUtil;
import ml.model.UserSwing;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class AuthUser {
    private static final String LOGIN_PASS = "from UserSwing u where u.login='";
    private List resultList;
    
    public void authenticationUser(String login) {
        executeHQLQuery(LOGIN_PASS + login + "'");
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
    public List<UserSwing> displayResult(){
        return resultList;
    }
}

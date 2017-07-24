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
 * Возвращает значение администратора
 *
 * @author dave
 */
public class AdminUser {

    private static final String LOGIN_PASS = "from UserSwing u where u.login='admin'";
    private List resultList;

    public void get() {
        executeHQLQuery(LOGIN_PASS);
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

    //Вывод админа
    public UserSwing displayResult() {
        UserSwing userSwing = new UserSwing();
        for (Object o : resultList) {
            userSwing = (UserSwing) o;
        }
        return userSwing;
    }
}

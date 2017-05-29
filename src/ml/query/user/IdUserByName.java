/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.user;

import ml.model.UserSwing;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает номер пользователя по его логину
 *
 * @author dave
 */
public class IdUserByName {

    private UserSwing user = new UserSwing();

    //Метод запроса по номеру карты
    public void setLoginUser(String login) {
        executeHQLQuery(login);
    }

    //HQL-запрос
    private void executeHQLQuery(String login) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();

            user = (UserSwing) session.createCriteria(UserSwing.class).add(Restrictions.eq("login", login)).uniqueResult();

            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }

    public UserSwing displayResult() {
        return user;
    }
}

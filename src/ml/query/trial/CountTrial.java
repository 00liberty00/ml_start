/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.trial;

import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *  Проверка, на наличие записи о триал версии
 * @author dave
 */

//Проверка, на наличие записи о триал версии
public class CountTrial {
    //select u.login, ur.authority  from UserSwing u, UserRoles ur where u.admin = ur.idUser and u.login = 'lena'
    private static final String COUNT_TRIAL = "select count(*) from Trial";
    private boolean result;
    private Object count;
    
    public void test() {
        executeHQLQuery(COUNT_TRIAL);
    }

    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            count = q.uniqueResult();
            
            if (Integer.parseInt(count.toString()) == 1){
                result = true;
            }
            else{
                result = false;
            }
            displayResult();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Вывод списка
    public boolean displayResult(){
        return result;
    }
}

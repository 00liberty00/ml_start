/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.auth;

import java.util.List;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Проверка есть ли записи в таблице пользователей
 *
 * @author dave
 */
public class AdminExists {

    private final Session sess = HibernateUtil.openSession();

    public boolean checkAdminExist() {
        boolean b = listUserSwing();
        return b;
    }

    private boolean listUserSwing() {

        boolean b = false;
        List l = sess.createQuery("from UserSwing").list();
        if (l.isEmpty()) {
            b = true;
        }

        return b;
    }
}

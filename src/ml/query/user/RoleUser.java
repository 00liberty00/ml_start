/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.user;

import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class RoleUser {

    private final Session sess = HibernateUtil.openSession();

    public Object listUserRoles(String login) {

        return sess.createQuery("select ur.authority from UserSwing u, "
                + "UserRoles ur where u.admin = ur.idRole and u.login = '"
                + login + "'").uniqueResult();
    }
}

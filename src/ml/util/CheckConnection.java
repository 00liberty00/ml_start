/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import org.hibernate.Session;

/**
 *
 * @author Dave
 */
public class CheckConnection {

    public CheckConnection() {
    }

    public Session restartConnection() {
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
        HibernateUtil.restartSessionFactory();

        Session sess = HibernateUtil.openSession();
        return sess;
    }

    public void closeConnection() {
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
        HibernateUtil.restartSessionFactory();
    }

}

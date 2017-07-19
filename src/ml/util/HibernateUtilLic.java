/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ml.crypt.SHAEncode;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author dave
 */
public class HibernateUtilLic {

    public static final ThreadLocal session = new ThreadLocal();
    private static Configuration configuration = new Configuration();
    private static final SessionFactory sessionFactory;
    private static SHAEncode sha = new SHAEncode();
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            //для внешнего приложения
            File f = new File("src/hibernate_license.cfg.xml");
            configuration = new Configuration().configure(f);
            
            //для внутреннего приложения
            //configuration = new Configuration().configure("/hibernate.cfg.xml");
            
            String path = "jdbc:mysql://185.25.117.219:3306/license_ml?autoReconnect=true&amp;useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull";
            configuration.setProperty("hibernate.connection.url", path);
            String pass = "plwej83jh";
            configuration.setProperty("hibernate.connection.password", pass);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session openSession() {
        Session s = (Session) session.get();
        if (s == null) {
            s = sessionFactory.openSession();
            session.set(s);
        }
        return s;
    }

    public static void closeSession() {
        Session s = (Session) session.get();

        if (s != null) {
            s.close();
        }
        session.set(null);
    }
}

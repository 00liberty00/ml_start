/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.trial;

import ml.util.HibernateUtil;
import ml.model.Trial;
import org.hibernate.Session;

/**
 *
 * @author dave
 */
public class TrialInfo {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public Trial getTrial() {

        return (Trial) sess.createQuery("from Trial").uniqueResult();
    }
    
    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {

        sess.clear();
    }
}

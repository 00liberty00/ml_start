/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.caserecord;

import ml.model.CaseRecord;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  Создает запись в Отчетах.
 * @author dave
 */
public class AddCaseRecord {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void add(CaseRecord caseRecord) {
        executeHQLQuery(caseRecord);
    }

    //HQL-запрос
    private void executeHQLQuery(CaseRecord caseRecord) {
        try {
            tx = session.beginTransaction();
            session.save(caseRecord);
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.util.List;
import ml.model.CheckList;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class CheckListByIdCheck {

    private final Session sess = HibernateUtil.openSession();

    private List<CheckList> resultCheckList;

    public void setIdCheck(Integer idCheck) {
        executeHQLQuery(idCheck);
    }

    //Продажи по дате
    private void executeHQLQuery(Integer idCheck) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();

        Criteria critCheckList = session.createCriteria(CheckList.class);
        Criteria suppCrit = critCheckList.createCriteria("check");
        critCheckList.createCriteria("goods");
        suppCrit.add(Restrictions.eq("idCheck", idCheck));
        resultCheckList = critCheckList.list();

        transaction.commit();
    }

    public List<CheckList> displayResult() {
        return resultCheckList;
    }

    @SuppressWarnings("unchecked")
    public List<CheckList> listCheck(Integer idCheck) {

        return sess.createQuery("from CheckList where check = " + idCheck)
                .list();
    }
}

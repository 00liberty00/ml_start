/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.time.LocalDate;
import java.util.List;
import ml.model.CheckList;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Расчет прибыли(разница между опт ценой и розницей)
 *
 * @author Dave
 */
public class ProfitCheck {

    private static final String PROFIT_CHECK = "from Check ch inner join ch.checkLists where ch.date >= '";
    private Object result;

    private final Session sess = HibernateUtil.openSession();

    @SuppressWarnings("unchecked")
    public List<CheckList> listCheckForProfit(LocalDate date) {

        return sess.createQuery("select cl from Check ch inner join ch.checkLists as cl where ch.date >= '" + date + " 00:00:00' and ch.date < '" + date + " 23:59:59'")
                .list();
    }

    //Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {

        sess.clear();
    }
}

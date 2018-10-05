/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.cancellation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.CategoryGoods;
import ml.model.WriteoffList;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Список чеков по дате
 *
 * @author Dave
 */
public class BetweenDatesCancellation {

    //from Check where date between '2017-12-14 00:00:00' and '2017-12-15 23:59:59' 
    private static final String QUERY_BASED_OF_GOODS = "from Writeoff where date between '";

    private List resultList;
    private BigDecimal arrivalAmount;
    private List resultListSumCancellation;
    
    private List resultListPlus;
    private BigDecimal sum = new BigDecimal(0.00);
    private BigDecimal sumCancellation = new BigDecimal(0.00);

    
    //Метод запроса списка списания по дням
    public void setDate(LocalDate dateFrom, LocalDate dateTo, CategoryGoods categoryGoods) {
        Date dateStart = Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //dateTo = dateTo + " 00:00:00";
        executeHQLQuery(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
        //executeHQLQuerySumCheck(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
    }

    //Добавить  00:00:00
    private Date addStartDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();

    }

    //Добавить  23:59:59
    private Date addEndDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();

    }

    //Сумма прихода по категории товара и дате
    private void executeHQLQuery(Date dateFrom, Date dateTo, CategoryGoods categoryGoods) {
        List<WriteoffList> wrList;
        sum = new BigDecimal(0.00);
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(WriteoffList.class);
        Criteria suppCrit = crit.createCriteria("writeoff");
        Criteria suppCritGoods = crit.createCriteria("goods");
        Criteria suppCritCategory = suppCritGoods.createCriteria("categoryGoods");

        suppCritCategory.add(Restrictions.eq("name", categoryGoods.getName()));
        //.createCriteria("checkLists").createCriteria("goods").createCriteria("categoryGoods");

        //больше или равно 
        // criteria.add(Restrictions.ge("date", dateFrom));
        //меньше чем
        //criteria.add(Restrictions.lt("date", addEndDay(dateTo)));
        //между
        suppCrit.add(Restrictions.between("date", dateFrom, addEndDay(dateTo)));

        resultListPlus = suppCritGoods.list();

        //Сумма продаж по категории товара 
        resultListSumCancellation = crit.list();
        wrList = resultListSumCancellation;
        for (int i = 0; i < wrList.size(); i++) {
            sum = sum.add(wrList.get(i).getGoods().getPrice().multiply(wrList.get(i).getAmount()));
        }

        displayResultPlus();
        displayResultSumCancellation();

        transaction.commit();
    }
    
    //HQL-запрос
    /*private void executeHQLQueryS(String hql) {
    try {
    Session session = HibernateUtil.openSession();
    session.beginTransaction();
    org.hibernate.Query q = session.createQuery(hql);
    resultList = q.list();
    displayResult();
    session.getTransaction().commit();
    } catch (HibernateException he) {
    he.printStackTrace();
    }
    }*/
    /*//Вывод списка
    public List<Writeoff> displayResult() {
    return resultList;
    }*/
    
    //Сумма списания по категории товара и дате
    public BigDecimal displayResultSumCancellation() {
        return sum;
    }

    //Вывод списка
    public List<WriteoffList> displayResultPlus() {
        return resultListPlus;
    }
}

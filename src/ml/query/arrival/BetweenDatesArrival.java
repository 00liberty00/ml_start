/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.arrival;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.Arrival;
import ml.model.ArrivalList;
import ml.model.CategoryGoods;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Список приходов по дате
 *
 * @author Dave
 */
public class BetweenDatesArrival {

    private List resultList;
    private BigDecimal arrivalAmount;
    private List<BigDecimal> resultListSumArrival;

    private List resultListPlus;
    private BigDecimal sum = new BigDecimal(0.00);
    private BigDecimal sumArrival = new BigDecimal(0.00);

    //Метод запроса списка прихода по дням
    /* public void setDate(Date dateFrom, Date dateTo) {
    
    executeHQLQuery(dateFrom, dateTo);
    }*/
    //Метод запроса списка прихода по дням
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
        List<Arrival> arList;
        sum = new BigDecimal(0.00);
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(ArrivalList.class);
        Criteria suppCrit = crit.createCriteria("arrival");
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

        Criteria critAr = session.createCriteria(Arrival.class);
        //Criteria suppCritAr = crit.createCriteria("arrival");
        //Criteria suppCritGoodsAr = crit.createCriteria("goods");
        critAr.add(Restrictions.eq("note", categoryGoods.getName()));

        critAr.add(Restrictions.between("date", dateFrom, addEndDay(dateTo)));
        critAr.setProjection(Projections.sum("sumArrival"));
        //Сумма прихода по категории товара 
        resultListSumArrival = critAr.list();

        if (resultListSumArrival.get(0) != null) {
            sum = resultListSumArrival.stream().reduce(BigDecimal.ZERO, BigDecimal::add);
        }
        displayResultPlus();
        displayResultSumArrival();

        transaction.commit();
    }

    //HQL-запрос
    private void executeHQLQueryS(Date dateFrom, Date dateTo) {
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Arrival.class);

        //больше или равно 
        criteria.add(Restrictions.ge("date", dateFrom));
        //меньше чем
        criteria.add(Restrictions.lt("date", dateTo));
        resultList = criteria.list();
        displayResult();
        transaction.commit();
    }

    //Вывод списка
    public List<Arrival> displayResult() {
        return resultList;
    }

    //Сумма прихода по категории товара и дате
    public BigDecimal displayResultSumArrival() {
        return sum;
    }

    //Вывод списка
    public List<ArrivalList> displayResultPlus() {
        return resultListPlus;
    }
}

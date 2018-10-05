/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import ml.model.CategoryGoods;
import ml.model.Check;
import ml.model.CheckList;
import ml.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 * Список чеков по дате
 *
 * @author Dave
 */
public class BetweenDatesCheck {

    private BigDecimal salesAmount;
    private List resultListSumCheck;

    private List resultListPlus;
    private BigDecimal sum = new BigDecimal(0.00);
    private BigDecimal sumCheck = new BigDecimal(0.00);

    //Метод запроса списка чеков по дням
    public void setDate(LocalDate dateFrom, LocalDate dateTo, CategoryGoods categoryGoods) {
        Date dateStart = Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //dateTo = dateTo + " 00:00:00";
        executeHQLQuery(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
        //executeHQLQuerySumCheck(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
    }

    //Метод запроса отчета по движению товара по дням
    public void setDateMoveGoods(LocalDate dateFrom, LocalDate dateTo) {
        Date dateStart = Date.from(dateFrom.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date dateEnd = Date.from(dateTo.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //dateTo = dateTo + " 00:00:00";
        //executeHQLQuery(addStartDay(dateStart), addEndDay(dateEnd), categoryGoods);
        executeHQLQuerySumCheck(addStartDay(dateStart), addEndDay(dateEnd));
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

    //Сумма продаж по категории товара и дате
    private void executeHQLQuery(Date dateFrom, Date dateTo, CategoryGoods categoryGoods) {
        List<CheckList> chList;
        sum = new BigDecimal(0.00);
        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(CheckList.class);
        Criteria suppCrit = crit.createCriteria("check");
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
        resultListSumCheck = crit.list();
        chList = resultListSumCheck;
        for (int i = 0; i < chList.size(); i++) {
            sum = sum.add(chList.get(i).getGoods().getPrice().multiply(chList.get(i).getAmount()));
        }

        displayResultPlus();
        displayResultSumCheck();

        transaction.commit();
    }

    //Сумма продаж по датам
    private void executeHQLQuerySumCheck(Date dateFrom, Date dateTo) {

        salesAmount = new BigDecimal(0.00);

        Session session = HibernateUtil.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Criteria crit = session.createCriteria(Check.class);

        //между
        crit.add(Restrictions.between("date", dateFrom, addEndDay(dateTo)));

        crit.setProjection(Projections.sum("sum"));
        salesAmount = (BigDecimal) crit.uniqueResult();

        displayResult();

        transaction.commit();
    }

    //Сумма продаж по дате
    public BigDecimal displayResult() {
        return salesAmount;
    }

    //Сумма продаж по категории товара и дате
    public BigDecimal displayResultSumCheck() {
        return sum;
    }

    //Вывод списка
    public List<CheckList> displayResultPlus() {
        return resultListPlus;
    }
}

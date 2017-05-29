/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import java.math.BigDecimal;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *  Сумма денег в товаре
 * @author Dave
 */
public class SumGoods {
    private static final String SUM = "select sum(residue * price) from Goods";
    private Object result;
    
    //Метод запроса Последнего чека
    public void sumGoods() {
        executeHQLQuery(SUM);
    }

    //HQL-запрос
    private void executeHQLQuery(String hql) {
        try {
            Session session = HibernateUtil.openSession();
            session.beginTransaction();
            org.hibernate.Query q = session.createQuery(hql);
            result = q.uniqueResult();
            
            getSumGoods();
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
    
    //Вывод списка
    public BigDecimal getSumGoods() {
        if (result != null) {
            return new BigDecimal(result.toString());
        }
        else{
            return new BigDecimal("0.00");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.indebtedness;

import java.time.LocalDate;
import ml.util.HibernateUtil;
import java.util.List;
import ml.model.Orders;
import org.hibernate.Session;

/**
 * Список задолжностей
 * @author dave
 */
public class IndebtList {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<Orders> listIndebtCategory(LocalDate date) {

        return sess.createQuery("from Orders  where date >= '" + date + "' order by date")
                .list();
    }
    
    /*//Очистка списка
    @SuppressWarnings("unchecked")
    public void clearList() {
    
    sess.clear();
    }*/
}

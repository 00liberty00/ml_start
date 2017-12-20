/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.check;

import java.util.List;
import ml.model.CheckList;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class CheckListByIdGood {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<CheckList> listCheck(Long idGoods) {
        
        return sess.createQuery("from CheckList where goods = " + idGoods)
                .list();
    }
}

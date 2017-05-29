/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.cancellation;

import java.util.List;
import ml.model.WriteoffList;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class CancelListByIdCancel {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<WriteoffList> listCancel(Long idCancel) {
        
        return sess.createQuery("from WriteoffList where writeoff = " + idCancel)
                .list();
    }
}

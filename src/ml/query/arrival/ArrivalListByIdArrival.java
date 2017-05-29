/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.arrival;

import java.util.List;
import ml.model.ArrivalList;
import ml.util.HibernateUtil;
import org.hibernate.Session;

/**
 * Вовзращает товар по коду
 *
 * @author dave
 */
public class ArrivalListByIdArrival {

    private final Session sess = HibernateUtil.openSession();
    
    @SuppressWarnings("unchecked")
    public List<ArrivalList> listArrival(Long idArrival) {
        
        return sess.createQuery("from ArrivalList where arrival = " + idArrival)
                .list();
    }
}

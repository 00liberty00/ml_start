/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.goods;

import java.math.BigDecimal;
import ml.model.ArrivalList;
import ml.model.CheckList;
import ml.model.Goods;
import ml.model.WriteoffList;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Dave
 */
public class UpdateResidueGood {

    Session session = HibernateUtil.openSession();
    Transaction tx = null;
    Session sessionFactory;

    public void update(CheckList checkList, ArrivalList arrivalList, WriteoffList cancelList, boolean addResidue) {
        executeHQLQuery(checkList, arrivalList, cancelList, addResidue);
    }

    //HQL-запрос
    private void executeHQLQuery(CheckList checkList, ArrivalList arrivalList, WriteoffList cancelList, boolean addResidue) {
        try {
            tx = session.beginTransaction();
            Goods goods = new Goods();
            BigDecimal bb = new BigDecimal(0.0);

            //Если addResidue == false, то вычитать из остатка, иначе прибавлять
            if (addResidue == false) {
                if (checkList != null) {
                    //вычитание из остатка при работе с чеком
                    goods = (Goods) session.load(Goods.class, checkList.getGoods().getIdGoods());
                    bb = goods.getResidue().subtract(checkList.getAmount());
                }
                else{
                    //вычитание из остатка при работе со списанием
                    goods = (Goods) session.load(Goods.class, cancelList.getGoods().getIdGoods());
                    bb = goods.getResidue().subtract(cancelList.getAmount());
                }
            } else {
                goods = (Goods) session.load(Goods.class, arrivalList.getGoods().getIdGoods());
                bb = goods.getResidue().add(arrivalList.getAmount());
            }
            goods.setResidue(bb);
            //discount.setName("David");
            session.update(goods);
            session.flush();  // changes to cat are automatically detected and persisted
            tx.commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        } finally {
            session.clear();
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.accounting;

import java.util.List;
import ml.model.Goods;
import ml.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Создает бекап базы товара
 * @author Dave
 */
public class BackupGoods {

    private Session sess;

    public void backupGoods() {
        executeHQLQuery();
    }

    /**
     * Список товара в бекапе
     * @return 
     */
    private List<Goods> listGoods() {
        
        return sess.createQuery("from GoodsBackup")
                .list();
    }

    //HQL-запрос
    private void executeHQLQuery() {
        try {
            sess = HibernateUtil.openSession();
            sess.beginTransaction();
            //Если таблица GoodsBackup пустая, то копировать
            if (listGoods().isEmpty()) {
                sess.createSQLQuery("insert into goodsbackup select * from goods g").executeUpdate();
            }

            sess.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
        }
    }
}

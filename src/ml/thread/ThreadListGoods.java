/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.thread;

import java.util.List;
import ml.model.Goods;
import ml.query.goods.QueryAllGoodsList;

/**
 *
 * @author Dave
 */
public class ThreadListGoods extends Thread {

    private QueryAllGoodsList allGoodsList = new QueryAllGoodsList();
    private List<Goods> mainGoodsList  ;

    
    @Override
    public void run() { // Этот метод будет вызван при старте потока
        //старт отдельного потока СПИСОК ТОВАРА
        System.out.println("Cтарт отдельного потока СПИСОК ТОВАРА");
        allGoodsList.clearList();
       
        mainGoodsList = allGoodsList.listGoods();
        try {
            sleep(500); // Задержка в 0.5 сек
        } catch (Exception e) {
        }
        System.out.println("Конец отдельного потока СПИСОК ТОВАРА");
    }

    public List<Goods> listGoods() {
        System.out.println("Вывод СПИСОК ТОВАРА");

        return mainGoodsList;
    }
}

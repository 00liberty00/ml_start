/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import ml.query.check.BetweenDatesCheck;

/**
 *
 * @author Dave
 */
public class NewThread extends Thread {

    private BetweenDatesCheck betweenDatesCheck = new BetweenDatesCheck();

    public NewThread(String str) {
        super(str);
    }

    public void run() {

        
        //список чеков по дате
        //betweenDatesCheck.setDate(dateFrom.getValue(), dateTo.getValue());
        //check = betweenDatesCheck.displayResult();
        
        System.out.println("= DONE: " + getName() + " =");
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import java.util.regex.Pattern;

/**
 *  Проверка ввода денег 0,00 или 0.00 .
 * @author dave
 */
public class MoneyRegexp {

    final String regExp = "[0-9]+([,.][0-9]{1,2})?";
    final Pattern pattern = Pattern.compile(regExp);

    public boolean check(String s) {
        if (s.matches(regExp)) {
            return true;
        } else {
            return false;
        }
    }

}

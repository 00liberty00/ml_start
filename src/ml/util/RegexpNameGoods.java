/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Проверка первого символа
 *
 * @author dave
 */
public class RegexpNameGoods {

    //Первый символ должен быть буквой
    public boolean nameGoods(String userNameString) {
        Pattern p = Pattern.compile("[a-zA-Zа-яА-Я]");
        if (!userNameString.equals("")) {
            int start = 0;
            int end = 1;
            char buf[] = new char[end - start];

            userNameString.getChars(start, end, buf, 0);

            String s = String.valueOf(buf);

            Matcher m = p.matcher(s);
            boolean b = m.matches();

            return m.matches();
        } else {
            return false;
        }
    }
}

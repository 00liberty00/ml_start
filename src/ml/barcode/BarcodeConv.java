/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.barcode;

import java.util.Arrays;

/**
 *
 * @author dave
 */
public class BarcodeConv {

    //Проверяет, есть ли совпадения в первых двух цифрах штрих-кода
    public boolean firstLetters(String code) {

        boolean firstLetter = false;

        int start = 0;
        int end = 2;
        char buf[] = new char[end - start];
        code.getChars(start, end, buf, 0);
        String s = String.valueOf(buf);

        if ("21".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("22".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("23".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("24".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("25".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("26".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("27".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("28".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        if ("29".equals(s)) {
            firstLetter = true;
            sixLetter(code);
            fourLetter(code);
        }
        return firstLetter;
    }

    //Выбирает из штрих-кода следующие 6 цифр
    public String sixLetter(String code) {
        int start = 2;
        int end = 8;
        char buf[] = new char[end - start];
        code.getChars(start, end, buf, 0);
        String s = String.valueOf(buf);
        System.out.println("След 6 цифр : " + s);
        return s;
    }
    //2201489209054

    //Выбирает из штрих-кода следующие 4 цифр
    public String fourLetter(String code) {
        StringBuffer sb = null;

        if (code.length() == 13) {

            int start = 8;
            int end = 12;
            char buf[] = new char[end - start];
            code.getChars(start, end, buf, 0);
            String s = String.valueOf(buf);

            sb = new StringBuffer(s);
            System.out.println("buffer before = " + sb);
            System.out.println("charAt(1) before = " + sb.charAt(1));
            sb.insert(1, ".");
            //sb.setLength(2);
            System.out.println("buffer after = " + sb);
            //System.out.println("charAt(1) after = " + sb.charAt(1));
            System.out.println("След 4 цифр : " + s);
        }
        return String.valueOf(sb);
    }

    public String createWeight(String code) {

        StringBuffer sb = null;

        if (code.length() == 2) {

            sb = new StringBuffer(code);
            sb.insert(0, "00");
        }
        if (code.length() == 3) {

            sb = new StringBuffer(code);
            sb.insert(0, "0");
        }
        if (code.length() == 4) {

            sb = new StringBuffer(code);
        }
        return String.valueOf(sb);
    }

    public String controlNumber(String code) {

        int counter = 0;//нечетное
        int summ = 0;   //четное

        for (int i = 0; i < code.length(); ++i) {
            int x = code.charAt(i) - '0';

            if (i % 2 == 0) {
                counter += x;
            } else {
                summ += x;
            }
        }
        int i1 = summ * 3 + counter;
        String s = String.valueOf(i1);

        int x = 0;
        for (int i = 0; i < s.length(); ++i) {
            x = s.charAt(i) - '0';
        }
        
        int control;
        if (x == 0) {
            control = 0;
        } else {
            control = 10 - x;
        }

        return code + String.valueOf(control);
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml;

/**
 *
 * @author Dave
 */
public class Ml {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("ОШИБКИ: ");
        //System.out.println("Не правильно считает сумму списания в отчетах о списании. Конкретно по чеку списания не верно считает ");
        System.out.println("");

        // TODO code application logic here
        if ("arm".equals(System.getProperty("os.arch"))) {
            System.out.println("ВАША Система ARM");
            //Runtime.getRuntime().exec("java -jar /media/user/STRONTIUM/MyCash_Rest/MyCash_Rest-1.0-SNAPSHOT.jar");
            Ml_Swing.main(args);
        } else {
            System.out.println("ВАША : " + System.getProperty("os.arch"));
            //Runtime.getRuntime().exec("java -jar ml.jar");
//            Ml_FX.main(args);
            Ml_FX.main(args);
        }
    }

}

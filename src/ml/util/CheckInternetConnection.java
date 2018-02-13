/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.Callable;
import ml.xml.XMLSearchAddress;

/**
 *
 * @author dave
 */
public class CheckInternetConnection implements Callable<String> {

    private final XMLSearchAddress xmlSearchAddress = new XMLSearchAddress();
    private boolean b;

    private boolean checkIntCon() {
        Boolean result = false;
        HttpURLConnection con = null;
        xmlSearchAddress.findAddress();
        String adressDB = xmlSearchAddress.displayResult();

        int serverPort = 3306; // случайный порт (может быть любое число от 1025 до 65535)
        try {
            InetAddress ipAddress = InetAddress.getByName(adressDB); // создаем объект который отображает вышеописанный IP-адрес.
            Socket socket = new Socket(ipAddress, serverPort);

            if (socket.isConnected()) {
                result = true;
            }
        } catch (Exception e) {
            //e.printStackTrace();
           // System.out.println("Связи нет!!!!!!");
        }

        return result;
    }

    @Override
    public String call() {
      
        return String.valueOf(checkIntCon());
    }

}

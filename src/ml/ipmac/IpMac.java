/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.ipmac;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 *
 * @author dave
 */
public class IpMac {

    private InetAddress ip;
    private StringBuilder sb = new StringBuilder();

    public String getIp() {
        try {
            ip = InetAddress.getLocalHost();
            //System.out.println("Current IP address : " + ip.getHostAddress());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return ip.toString();
    }

    public String getMac() {

        try {
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            //System.out.print("Current MAC address : ");

            sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());

        } catch (SocketException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }
    
     public String getName() {
        try {
            ip = InetAddress.getLocalHost();
            System.out.println("Current IP address : " + ip.getHostName());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        return ip.getHostName();
    }
    

}

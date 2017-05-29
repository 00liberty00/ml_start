/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.crypt;

import ml.xml.XMLSearchPass;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.*;

/**
 *
 * @author dave
 */
public class SHAEncode {

    public String passEncode;
    public String passDecode;
    private XMLSearchPass xmls = new XMLSearchPass();

    public void newKey() throws Exception {
        resultKey();
    }
    
    public void encode(String s) throws Exception {
        resultEncode(s);
    }

    public void decode() throws Exception {
        resultDecode();
    }

    private void resultKey() throws Exception {

        FileOutputStream fos = null;
        try {
            String keyfile = "key.key";
            String algorithm = "DESede";

            KeyGenerator kg = KeyGenerator.getInstance(algorithm);
            SecretKey key = kg.generateKey();

            fos = new FileOutputStream(keyfile);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            DESedeKeySpec keyspec = (DESedeKeySpec) skf.getKeySpec(key, DESedeKeySpec.class);
            fos.write(keyspec.getKey());
            fos.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void resultEncode(String s) throws Exception {

        FileOutputStream fos = null;
        String secret = s;
        FileInputStream fis = null;
        try {
            String keyfile = "key.key";
            String algorithm = "DESede";
            fis = new FileInputStream(keyfile);
            byte[] keyspecbytes = new byte[fis.available()];
            fis.read(keyspecbytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            DESedeKeySpec keyspec = new DESedeKeySpec(keyspecbytes);
            SecretKey key = skf.generateSecret(keyspec);
            Cipher cipher = Cipher.getInstance(algorithm);
            fis.close();
            
            cipher.init(Cipher.ENCRYPT_MODE, key);

            
            byte[] utf8 = s.getBytes("UTF8");
            byte[] enc = cipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            passEncode = new String(enc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resultDecode() throws Exception {

        FileInputStream fis = null;
        try {
            String keyfile = "key.key";
            String algorithm = "DESede";
            fis = new FileInputStream(keyfile);
            byte[] keyspecbytes = new byte[fis.available()];
            fis.read(keyspecbytes);
            SecretKeyFactory skf = SecretKeyFactory.getInstance(algorithm);
            DESedeKeySpec keyspec = new DESedeKeySpec(keyspecbytes);
            SecretKey key = skf.generateSecret(keyspec);
            Cipher cipher = Cipher.getInstance(algorithm);
            fis.close();

            // Initialize the same cipher for decryption
            cipher.init(Cipher.DECRYPT_MODE, key);
            xmls.findPass();
            String s = xmls.displayResult();
            byte[] dec = BASE64DecoderStream.decode(s.getBytes());
            byte[] utf8 = cipher.doFinal(dec);
            passDecode = new String(utf8, "UTF8");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String displayEncode() {
        return passEncode;
    }
    public String displayDecode() {
        return passDecode;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.*;
import ml.exit.ExitApp;
import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTPReply;

public class UpdateApp {

    /*
    public void downloadApp() {
    String server = "crimeali.ftp.tools";
    int port = 21;
    String user = "crimeali_ml_s";
    String pass = "Day3HZ8iB1p7";
    
    FTPClient ftpClient = new FTPClient();
    try {
    
    ftpClient.connect(server, port);
    ftpClient.login(user, pass);
    ftpClient.enterLocalPassiveMode();
    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
    
    // APPROACH #1: using retrieveFile(String, OutputStream)
    String remoteFile1 = "/home/crimeali/ml_s/hello.txt";
    File downloadFile1 = new File("X:/NetBeans/MarLeo/ml_s/1.txt");
    boolean success;
    try (OutputStream outputStream1 = new BufferedOutputStream(new FileOutputStream(downloadFile1))) {
    success = ftpClient.retrieveFile(remoteFile1, outputStream1);
    }
    
    if (success) {
    System.out.println("File #1 has been downloaded successfully.");
    }
    
    
    } catch (IOException ex) {
    System.out.println("Error: " + ex.getMessage());
    ex.printStackTrace();
    } finally {
    try {
    if (ftpClient.isConnected()) {
    ftpClient.logout();
    ftpClient.disconnect();
    }
    } catch (IOException ex) {
    ex.printStackTrace();
    }
    }
    }*/
    public UpdateApp() {

    }

    FTPClient ftp = null;
//"crimeali.ftp.tools", "crimeali_ml_s", "Day3HZ8iB1p7"

    public UpdateApp(String host, String user, String pwd) throws Exception {
        ftp = new FTPClient();
        ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
        int reply;
        ftp.connect(host);
        reply = ftp.getReplyCode();
        if (!FTPReply.isPositiveCompletion(reply)) {
            ftp.disconnect();
            throw new Exception("Exception in connecting to FTP Server");
        }
        ftp.login(user, pwd);
        ftp.setFileType(FTP.BINARY_FILE_TYPE);
        ftp.enterLocalPassiveMode();
    }

    public void downloadFile(String remoteFilePath, String localFilePath) {
        try (FileOutputStream fos = new FileOutputStream(localFilePath)) {
            this.ftp.retrieveFile(remoteFilePath, fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() {
        if (this.ftp.isConnected()) {
            try {
                this.ftp.logout();
                this.ftp.disconnect();
            } catch (IOException f) {
                // do nothing as file is already downloaded from FTP server
            }
        }
    }

    public void downloadApp() {
        try {
            UpdateApp ftpDownloader
                    = new UpdateApp("crimeali.ftp.tools", "crimeali_ml_s", "Day3HZ8iB1p7");
            ftpDownloader.downloadFile("ml_s.jar", "ml_s.jar");
            //ftpDownloader.downloadFile("hello.txt", "hello.txt");

            System.out.println("FTP File downloaded successfully");
            ftpDownloader.disconnect();

            ExitApp app = new ExitApp();
            app.close();
            //Platform.exit();

            //Ml m = new Ml();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

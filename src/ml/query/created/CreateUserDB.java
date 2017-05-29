/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.created;

import com.mysql.jdbc.Connection;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author dave
 */
public class CreateUserDB {

    private static String sourceDB;
    private static String port;
    private static String user;
    private static String password;
    private static String userServer;
    private static String passwordServer;

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            //185.69.153.77:3306
            dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://" + sourceDB + ":" + port + "/?user=" + userServer + "&password=" + passwordServer);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    private static Connection getNewDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            //185.69.153.77:3306
            dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://" + sourceDB + ":" + port + "/marleo?user=" + user + "&password=" + password);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    //Создает пользователя
    public void addLocalUser(String sourceDB, String port, String userServer, String passwordServer, String user, String password) {

        CreateUserDB.sourceDB = sourceDB;
        CreateUserDB.user = user;
        CreateUserDB.password = password;
        CreateUserDB.port = port;
        CreateUserDB.userServer = userServer;
        CreateUserDB.passwordServer = passwordServer;
        Connection dbConnection = null;
        Statement statement = null;

        String createUser = "CREATE USER '" + user + "'@'" + sourceDB + "' IDENTIFIED BY '" + password + "';";
        String createPrivileges = "GRANT ALL PRIVILEGES ON marleo.* TO '" + user + "'@'" + sourceDB + "';";
        String flushPrivileges = "FLUSH PRIVILEGES;";
        String createDB = "CREATE DATABASE marleo;";

        //Создание пользователя и привилегий
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            statement.executeUpdate(createUser);
            statement.executeUpdate(createDB);
            statement.executeUpdate(createPrivileges);
            statement.executeUpdate(flushPrivileges);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        String s = new String();
        StringBuffer sb = new StringBuffer();

        //Запись таблиц в БД
        try {
            FileReader fr = new FileReader(new File("database.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            Connection c = getNewDBConnection();
            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }

    }

    //Создает пользователя
    public void addRemoteUser(String sourceDB, String port, String user, String password) {

        CreateUserDB.sourceDB = sourceDB;
        CreateUserDB.port = port;
        CreateUserDB.user = user;
        CreateUserDB.password = password;
        
        Connection dbConnection = null;
        Statement statement = null;

        //String createUser = "CREATE USER 'neww'@'localhost' IDENTIFIED BY 'secret';";
        //String createPrivileges = "GRANT SELECT, INSERT, DELETE ON codescanner TO username@'localhost' IDENTIFIED BY 'secret';";
        //String createDB = "CREATE DATABASE codescanner";

        String s = new String();
        StringBuffer sb = new StringBuffer();

        //Запись таблиц в БД
        try {
            FileReader fr = new FileReader(new File("database.sql"));
            // be sure to not have line starting with "--" or "/*" or any other non aplhabetical character

            BufferedReader br = new BufferedReader(fr);

            while ((s = br.readLine()) != null) {
                sb.append(s);
            }
            br.close();

            // here is our splitter ! We use ";" as a delimiter for each request
            // then we are sure to have well formed statements
            String[] inst = sb.toString().split(";");

            Connection c = getNewDBConnection();
            Statement st = c.createStatement();

            for (int i = 0; i < inst.length; i++) {
                // we ensure that there is no spaces before or after the request string
                // in order to not execute empty statements
                if (!inst[i].trim().equals("")) {
                    st.executeUpdate(inst[i]);
                    System.out.println(">>" + inst[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("*** Error : " + e.toString());
            System.out.println("*** ");
            System.out.println("*** Error : ");
            e.printStackTrace();
            System.out.println("################################################");
            System.out.println(sb.toString());
        }
        
        
        
        
        /*try {
        dbConnection = getDBConnection();
        statement = dbConnection.createStatement();
        
        statement.executeUpdate(createUser);
        
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }*/

        //Вход с по новому пользователю
        /*try {
        dbConnection = getNewDBConnection();
        statement = dbConnection.createStatement();
        
        statement.executeUpdate(createDB);
        statement.executeUpdate(createPrivileges);
        
        } catch (SQLException e) {
        System.out.println(e.getMessage());
        }*/
    }

}

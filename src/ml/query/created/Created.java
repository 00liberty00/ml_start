/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.created;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import ml.model.Comp;

/**
 *
 * @author dave
 */
public class Created {

    String idLicense;

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = (Connection) DriverManager.getConnection("jdbc:mysql://crimeali.mysql.ukraine.com.ua/crimeali_license", "crimeali_license", "2bet2lx6");
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }

    //Выводит номер лицензии
    public String select(String l1) {
        String license = null;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select license, id_license from license";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает   
            while (rs.next()) {
                if (l1.equals(rs.getString("license"))) {
                    idLicense = rs.getString("id_license");
                    license = rs.getString("license");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return license;
    }

    public String DisplayIdLicense(){
        return idLicense;
    }
    
    //Обновляет запись
    public String update() {
        String license = null;
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "update license set created = true where id_license = " + idLicense;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);

            System.out.println("Record is updated to DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return license;
    }

    //Выводит использована лицензия или нет
    public boolean useLicense() {
        boolean use = false;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select created from license where id_license = " + idLicense;
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает   
            while (rs.next()) {
                use = rs.getBoolean("created");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return use;
    }

    //Добавляет +1 рабочее приложение к ключу
    public void addUser(String license) {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "update license set includeUser = includeUser + 1 where license = '" + license + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);

            System.out.println("Record is updated to DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //удаляет рабочее приложение к ключу
    public void deleteUser(String license) {
        Connection dbConnection = null;
        Statement statement = null;
        String updateTableSQL = "update license set includeUser = includeUser - 1 where license = '" + license + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);

            System.out.println("Record is updated to DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Выводит кол-во подкл пользователей
    public Integer getUser(String license) {
        Integer countUser = 0;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select includeUser from license where license = '" + license + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает   
            while (rs.next()) {
                countUser = Integer.parseInt(rs.getString("includeUser"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countUser;
    }

    //Выводит кол-во возможных подключений
    public Integer getCountPC(String license) {
        Integer countUser = 0;
        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select countPC from license where license = '" + license + "'";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает   
            while (rs.next()) {
                countUser = Integer.parseInt(rs.getString("countPC"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countUser;
    }

    //Добавляет mac компа в БД
    public void insertMac(Comp comp) {
        Connection dbConnection = null;
        Statement statement = null;

//        String insertTableSQL = "insert into user (id_user, mac, ip, name, date_create, note, blocking, id_license)\n"
        String insertTableSQL = "insert into user (id_user, mac, name, blocking, date_create)\n"
                + "values (?,?,?,?,?);";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            PreparedStatement pstmt = dbConnection.prepareStatement(insertTableSQL);
            pstmt.setString(1, null);
            pstmt.setBytes(2, comp.getMac());
            pstmt.setString(3, comp.getName());
            pstmt.setBoolean(4, comp.isBlocking());
            pstmt.setDate(5, new java.sql.Date( comp.getDateCreate().getTime()));
            //pstmt.setInt(6, comp.getIdLicense());
            pstmt.execute();

            // выполняем запрос update SQL
            //statement.execute(insertTableSQL);
            System.out.println("Record is updated to DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Проверяет mac компа в БД
    public boolean searchMac(String macString) {
        boolean checkMac = false;

        Connection dbConnection = null;
        Statement statement = null;
        String selectTableSQL = "select hex(mac) from user";
        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);
            byte[] macAddress = new byte[12];
            // И если что то было получено то цикл while сработает   
            while (rs.next()) {
                //countUser = Integer.parseInt(rs.getString("countPC"));
                for (int i = 0; i < 12; i++) {
//                    Integer hex = Integer.parseInt("mac", 16);
//                    macAddress[i] = hex.byteValue();
                    macAddress[i] = rs.getBytes(1)[i];
                    //System.out.println("ОТВЕТ : " + new String(rs.getBytes(1)));
                }

                System.out.println("ОТВЕТ_БД : " + new String(macAddress));
                if (macString.equals(new String(macAddress))) {
                    checkMac = true;
                    System.out.println("КРУТЬ!!!");
                }
            }
            System.out.println("ОТВЕТ_КЛАСС : " + macString);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return checkMac;
    }

}

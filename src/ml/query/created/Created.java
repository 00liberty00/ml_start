/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.query.created;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

    
}

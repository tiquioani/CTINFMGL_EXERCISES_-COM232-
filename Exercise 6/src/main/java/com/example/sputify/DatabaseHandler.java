package com.example.sputify;
import Model.User;

import java.sql.*;

public class DatabaseHandler {

    private static DatabaseHandler handler = null;
    private static Statement stmt = null;
    private static PreparedStatement pstatement = null;

    public static DatabaseHandler getInstance() {
        if (handler == null) {
            handler = new DatabaseHandler();
        }
        return handler;
    }

    public static Connection getDBConnection() {
        Connection connection = null;

        String dburl = "jdbc:mysql://localhost:3306/users?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String userName = "root";
        String password = "WJ28@krhps";

        try {
            connection = DriverManager.getConnection(dburl, userName, password);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public ResultSet execQuery(String query) {

        ResultSet result;

        try {
            stmt = getDBConnection().createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }

        return result;
    }

    public static boolean validateLogin(String username, String password) {

        getInstance();
        String query = "SELECT * FROM user WHERE UserName = '" + username + "' AND Password = '" + password + "'";

        System.out.println(query);

        ResultSet result = handler.execQuery(query);
        try {
            if (result.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    //GET USERS
    public static ResultSet getUsers(){

        ResultSet result = null;

        try{
            String query = "SELECT * FROM user";
            result = handler.execQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean addUser(User user) {

        try {
            // Update the SQL to include accountcreated field
            pstatement = getDBConnection().prepareStatement(
                    "INSERT INTO user (Username, Password, AccountStatus, AccountCreated) VALUES (?,?,?,NOW())"
            );
            pstatement.setString(1, user.getUsername());
            pstatement.setString(2, user.getPassword());
            pstatement.setString(3, user.getAccountstatus());
            return pstatement.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }

    public static boolean deleteUser(User user) {
        try {
            String deleteStatement = "DELETE FROM `user` WHERE Username = ?";
            pstatement = getDBConnection().prepareStatement(deleteStatement);
            pstatement.setString(1, user.getUsername());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean updateUser(User user) {
        try {
            String updateStatement = ("UPDATE `user` SET Username = ?, Password = ?, AccountStatus = ? WHERE Username = ?");
            pstatement = getDBConnection().prepareStatement(updateStatement);
            pstatement.setString(1, user.getUsername());
            pstatement.setString(2, user.getPassword());
            pstatement.setString(3, user.getAccountstatus());
            pstatement.setString(4, user.getUsername());
            int res = pstatement.executeUpdate();
            if (res > 0) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
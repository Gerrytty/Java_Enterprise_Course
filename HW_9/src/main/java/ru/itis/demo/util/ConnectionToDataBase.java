package ru.itis.demo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {

    private static Connection connection;

    private static String URL = "jdbc:mysql://127.0.0.1:3306/java_lab_HW?serverTimezone=UTC";
    private static String USER = "root";
    private static String PASSWORD = "pass";

    private ConnectionToDataBase() {

    }

    public static Connection getConnection() {
        if(connection == null) {
            createConnection();
        }
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection to data base failed");
        }
    }

}

package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionToDataBase {
    private static String url;
    private static String user;
    private static String password;
    private static String port;
    private static Connection connection;

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
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to data base failed");
        }
    }

    public static void setProperties(Properties properties) {
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        port = properties.getProperty("port");
        url = properties.getProperty("url");
    }

}

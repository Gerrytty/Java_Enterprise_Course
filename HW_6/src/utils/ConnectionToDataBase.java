package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionToDataBase {

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
            connection = DriverManager.getConnection(DataBase.getUrl(), DataBase.getUser(), DataBase.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection to data base failed");
        }
    }


}

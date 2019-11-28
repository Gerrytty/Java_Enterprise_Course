package server;

import DAO.UserDAO;
import ORM.User;
import utils.ConnectionToDataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ChatServerStartMain {
    private static int port = 7000;
    private static String path = "db.properties";

    public static void main(String[] args) {

        connectToDataBase(path);

        MultiClientServer multiClientServer = new MultiClientServer();
        multiClientServer.start(port);
    }

    private static Connection connectToDataBase(String path) {

        Properties properties = new Properties();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ConnectionToDataBase.setProperties(properties);

        return ConnectionToDataBase.getConnection();
    }

    public static void setPortAndPath(String... args) {

        if(args.length >= 2) {
            port = Integer.parseInt(args[0].substring(args[0].lastIndexOf("=") + 1));
            path = args[1].substring(args[1].lastIndexOf("=") + 1);
        }

    }
}
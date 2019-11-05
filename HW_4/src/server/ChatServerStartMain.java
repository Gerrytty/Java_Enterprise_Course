package server;

import some_classes.ConnectionToDataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ChatServerStartMain {
    public static void main(String[] args) {
       int port = Integer.parseInt(args[0].substring(args[0].lastIndexOf("=") + 1));
       String path = args[1].substring(args[1].lastIndexOf("=") + 1);
        // String path = "db.properties";
        connectToDataBase(path);

        MultiClientServer multiClientServer = new MultiClientServer();
        // multiClientServer.start(7000);
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
}
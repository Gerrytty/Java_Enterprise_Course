package server;

import utils.ConnectionToDataBase;
import utils.DataBase;

public class ChatServerStartMain {

    private static int port = 7000;
    private static String path = "db.properties";

    public static void main(String[] args) {

        setPortAndPath(args);

        connectToDataBase();

        MultiClientServer multiClientServer = new MultiClientServer();
        multiClientServer.start(port);
    }

    public static void setPortAndPath(String... args) {

        if(args.length >= 2) {
            port = Integer.parseInt(args[0].substring(args[0].lastIndexOf("=") + 1));
            path = args[1].substring(args[1].lastIndexOf("=") + 1);
        }

    }

    private static void connectToDataBase() {

        DataBase.setProperties(path);

        ConnectionToDataBase.getConnection();

    }
}
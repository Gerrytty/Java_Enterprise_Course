package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBase {

    public static String dataBaseName = "java_lab_HW";

    private static String url;
    private static String user;
    private static String password;

    private static String port;


    public static void setProperties(String path) {

        Properties properties = new Properties();

        FileInputStream fileInputStream;

        try {
            fileInputStream = new FileInputStream(path);
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        user = properties.getProperty("user");
        password = properties.getProperty("password");
        port = properties.getProperty("port");
        url = properties.getProperty("url");
    }

    public static String getDataBaseName() {
        return dataBaseName;
    }

    public static String getUrl() {
        return url;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUser() {
        return user;
    }
}

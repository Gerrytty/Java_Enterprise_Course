import DAO.UserDAO;
import ORM.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import commands.Request;
import utils.ConnectionToDataBase;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        connectToDataBase("db.properties");

        ObjectMapper mapper = new ObjectMapper();
        String header = mapper.readValue("{\"header\":\"login\",\"payload\":{\"email\":\"login\",\"password\":\"pass\"}}", Request.class).getHeader();
        System.out.println(header);

        User u = new User();
        u.setUser_id(9);
        new UserDAO().delete(u);
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

package ORM;

import some_classes.ConnectionToDataBase;

import java.sql.*;

public class User {
    private int user_id;
    private String login;
    private String password;

    private String sql = "INSERT INTO java_lab_HW.User (login, password)" + "VALUES (?, ?)";
    private String query = "SELECT login FROM java_lab_HW.User";
    private static String getId = "SELECT login, user_id FROM java_lab_HW.User";

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void addToDB() {
        if(!isExists()) {
            try {

                PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("adding to data base failed");
            }
        }
    }

    private boolean isExists() {
        try {
            Statement st = ConnectionToDataBase.getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String login = rs.getString("login");
                if(this.login.equals(login)) {
                    return true;
                }
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }

    public static int getUser_id(String login) {
        try {
            Statement st = ConnectionToDataBase.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getId);

            while (rs.next()) {
                String log = rs.getString("login");
                if(log.equals(login)) {
                    return rs.getInt("user_id");
                }
            }
            st.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return 0;
    }
}

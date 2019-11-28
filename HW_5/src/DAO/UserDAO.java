package DAO;

import ORM.User;
import utils.ConnectionToDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {
    private User user;

    private String sql = "INSERT INTO java_lab_HW.User (login, password)" + "VALUES (?, ?)";
    private String login = "SELECT login FROM java_lab_HW.User";
    private static String getId = "SELECT login, user_id FROM java_lab_HW.User";

    public UserDAO() {

    }

    public UserDAO(User user) {
        this.user = user;
    }

    private boolean isExists(User user) {
        try {
            Statement st = ConnectionToDataBase.getConnection().createStatement();
            ResultSet rs = st.executeQuery(login);

            while (rs.next()) {
                String login = rs.getString("login");

                if(user.getLogin().equals(login)) {
                    return true;
                }
            }
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public int getUser_id(User user) {

        if (user.getUser_id() != 0) {
            return user.getUser_id();
        }

        try {

            Statement st = ConnectionToDataBase.getConnection().createStatement();
            ResultSet rs = st.executeQuery(getId);

            while (rs.next()) {

                String log = rs.getString("login");

                if(log.equals(user.getLogin())) {

                    int id = rs.getInt("user_id");
                    user.setUser_id(id);

                    return id;
                }
            }

            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }

    public int getUser_id() {
        return getUser_id(this.user);
    }

    public void save() {
        save(this.user);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void delete(User user) {
        String delete = "delete from java_lab_HW.User where user_id = ?";

        try {
            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(delete);
            preparedStatement.setInt(1, user.getUser_id());
            preparedStatement.executeUpdate();

        }
        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("deleting to data base failed");
        }

    }

    @Override
    public void update(User user, String[] params) {

    }

    @Override
    public void save(User user) {

        if(!isExists(user)) {
            try {

                PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(sql);
                preparedStatement.setString(1, user.getLogin());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.executeUpdate();

            }
            catch (SQLException e) {
                e.printStackTrace();
                System.out.println("adding to data base failed");
            }
        }

        else {
            System.out.println("User is exists");
        }
    }
}

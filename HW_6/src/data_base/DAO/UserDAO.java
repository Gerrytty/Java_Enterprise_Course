package data_base.DAO;

import data_base.DAO.ORM.User;
import utils.ConnectionToDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserDAO implements DAO<User> {
    private User user;

    private String sql = "INSERT INTO java_lab_HW.User (login, password)" + "VALUES (?, ?)";


    public UserDAO() {

    }

    public UserDAO(User user) {
        this.user = user;
    }


    public void save() {
        save(this.user);
    }

    @Override
    public Optional<User> get(long id) {

        return Optional.empty();
    }


    public User get(String userLogin) {

        String select = "SELECT * from java_lab_HW.User where login = ?";

        User user = new User();

        try {

            PreparedStatement ps = ConnectionToDataBase.getConnection().prepareStatement(select);
            ps.setString(1, userLogin);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String login = rs.getString("login");

                if (login == null) {
                    return null;
                }

                else {
                    user.setUser_id(rs.getInt("user_id"));
                    user.setLogin(rs.getString("login"));
                    user.setPassword(rs.getString("password"));
                }

                return user;

            }

            ps.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
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
}

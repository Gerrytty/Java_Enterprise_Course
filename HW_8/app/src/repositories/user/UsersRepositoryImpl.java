package repositories.user;

import context.Component;
import model.User;
import repositories.RowMapper;
import utils.ConnectionToDataBase;
import utils.Password;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryImpl implements UsersRepository, Component {

    private Connection connection;

    private RowMapper<User> rowMapper = rs -> new User(
            rs.getInt("user_id"),
            rs.getString("login"),
            rs.getString("password"),
            rs.getInt("role")
    );

    public UsersRepositoryImpl() {
        connection = ConnectionToDataBase.getConnection();
    }

    public Optional<User> findByLogin(String login) {

        String selectString = "select * from java_lab_HW.User where login = ? LIMIT 1";

        try (PreparedStatement ps = connection.prepareStatement(selectString)) {

            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            User user = null;

            if (rs.next()) {
                user = rowMapper.mapRow(rs);
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void save(User user) {

        String insertString = "insert into java_lab_HW.User(login, password) values (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setString(1, user.getLogin());
            ps.setString(2, Password.hash(user.getPassword()));
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("adding user to data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void delete(int id) {

    }


    @Override
    public Optional<User> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public String getName() {
        return "usersRepository";
    }
}

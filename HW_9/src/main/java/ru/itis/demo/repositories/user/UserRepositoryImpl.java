package ru.itis.demo.repositories.user;
import lombok.NoArgsConstructor;
import ru.itis.demo.model.NetflixUser;
import ru.itis.demo.repositories.RowMapper;
import ru.itis.demo.repositories.movie.MovieRepository;
import ru.itis.demo.util.ConnectionToDataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private Connection connection;

    private String insertString = "insert into NetflixUser(firstName, lastName, email, password, birthDate)" +
            " values (?, ?, ?, ?, ?)";

    private String selectString = "select * from NetflixUser where id = ? LIMIT 1";

    private String select = "select * from NetflixUser";

    private String deleteString = "delete from NetflixUser where id = ?";

    private RowMapper<NetflixUser> mapper = rs -> new NetflixUser(
            rs.getLong("id"),
            rs.getString("firstName"),
            rs.getString("lastName"),
            rs.getString("email"),
            rs.getString("password"),
            rs.getDate("birthDate")
    );

    private MovieRepository movieRepository;

    public UserRepositoryImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
        this.connection = ConnectionToDataBase.getConnection();
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public UserRepositoryImpl(Connection connection, MovieRepository movieRepository) {
        this.connection = connection;
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<NetflixUser> findOne(Long id) {

        try (PreparedStatement ps = connection.prepareStatement(selectString)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            NetflixUser user = null;

            if (rs.next()) {
                user = mapper.mapRow(rs);
                user.setWatchedMovies(movieRepository.findAllByUserId(user.getId()));
            }

            return Optional.ofNullable(user);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<NetflixUser> findAll() {

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(select);
            List<NetflixUser> list = new ArrayList<>();

            while (rs.next()) {
                NetflixUser user = mapper.mapRow(rs);
                user.setWatchedMovies(movieRepository.findAllByUserId(user.getId()));
                list.add(user);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void save(NetflixUser netflixUser) {

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setString(1, netflixUser.getFirstName());
            ps.setString(2, netflixUser.getLastName());
            ps.setString(3, netflixUser.getEmail());
            ps.setString(4, netflixUser.getPassword());
            ps.setDate(5, (Date) netflixUser.getBirthDate());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("adding user to data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void delete(long id) {

        try (PreparedStatement ps = connection.prepareStatement(deleteString)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("deleting user from data base failed");
            throw new IllegalStateException(e);
        }

    }
}

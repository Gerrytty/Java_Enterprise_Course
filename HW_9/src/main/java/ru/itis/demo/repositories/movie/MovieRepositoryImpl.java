package ru.itis.demo.repositories.movie;

import ru.itis.demo.model.Movie;
import ru.itis.demo.repositories.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MovieRepositoryImpl implements MovieRepository {

    private Connection connection;

    private String insertString = "insert into Movie(name, producer, yearOfIssue, userId) values (?, ?, ?, ?)";
    private String selectAllString = "select * from Movie";
    private String selectString = "select * from Movie where id = ? limit 1";
    private String deleteString = "delete from Movie where id = ?";

    private RowMapper<Movie> mapper = rs -> new Movie(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("producer"),
            rs.getDate("yearOfIssue"),
            rs.getLong("userId")
    );

    public MovieRepositoryImpl(Connection connection) {
        this.connection = connection;
    }



    @Override
    public Optional<Movie> findOne(Long id) {

        try (PreparedStatement ps = connection.prepareStatement(selectString)) {

            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            Movie movie = null;

            if (rs.next()) {
                movie = mapper.mapRow(rs);
            }

            return Optional.ofNullable(movie);

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Movie> findAll() {

        try (Statement statement = connection.createStatement()) {

            ResultSet rs = statement.executeQuery(selectAllString);
            List<Movie> list = new ArrayList<>();

            while (rs.next()) {
                list.add(mapper.mapRow(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Movie movie) {

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setString(1, movie.getName());
            ps.setString(2, movie.getProducer());
            ps.setDate(3, (Date) movie.getYearOfIssue());
            ps.setLong(4, movie.getUserId());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("adding movie to data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void delete(long id) {

        try (PreparedStatement ps = connection.prepareStatement(deleteString)) {
            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("deleting movie from data base failed");
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Movie> findAllByUserId(Long userId) {

        String find = "select * from Movie where userId = ?";

        List<Movie> list = new ArrayList<>();

        try (PreparedStatement ps = connection.prepareStatement(find)) {

            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Movie movie = mapper.mapRow(rs);
                list.add(movie);
            }

            return list;

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

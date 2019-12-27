package repositories.message;

import context.Component;
import dto.MessageDto;
import model.Message;
import repositories.RowMapper;
import utils.ConnectionToDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class MessagesRepositoryImpl implements MessagesRepository, Component {

    private Connection connection;

    private RowMapper<MessageDto> messageRowMapper = rs -> new MessageDto(
            rs.getString("login"),
            rs.getString("message")
    );

    public MessagesRepositoryImpl() {
        connection = ConnectionToDataBase.getConnection();
    }

    @Override
    public Optional<Message> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Message> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void save(Message message) {
        String insertString = "insert into java_lab_HW.Message(user_id, text) VALUES (?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(insertString)) {

            ps.setString(1, message.getText());
            ps.setInt(2, message.getUserId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public Optional<List<MessageDto>> findByPage(int page) {
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "messagesRepository";
    }
}

package DAO;

import ORM.Message;
import commands.PaginationRequest;
import utils.ConnectionToDataBase;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageDAO implements DAO<Message> {

    @Override
    public Optional<Message> get(long id) {
        return Optional.empty();
    }

    @Override
    public List<Message> getAll() {
        return null;
    }

    @Override
    public void save(Message message) {

        String insertString = "INSERT INTO java_lab_HW.Message (user_id, text, datetime)" + "VALUES (?, ?, ?)";

        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(insertString);
            preparedStatement.setInt(1, message.getUser_id());
            preparedStatement.setString(2, message.getText());
            preparedStatement.setDate(3, message.getDatetime());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("adding to data base failed");
        }
    }

    @Override
    public void update(Message message, String[] params) {

    }

    @Override
    public void delete(Message message) {

    }

    public List<Message> pagination(PaginationRequest request) {

        String pagination = "select * from Message limit ?, ?";

        List<Message> list = new ArrayList<>();

        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(pagination);
            preparedStatement.setInt(1, request.getStart());
            preparedStatement.setInt(2, request.getEnd());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Message message = new Message();
                message.setMessage_id(rs.getInt("message_id"));
                message.setText(rs.getString("text"));

                list.add(message);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("adding to data base failed");
        }

        return list;
    }
}

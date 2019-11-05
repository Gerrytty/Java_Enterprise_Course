package ORM;

import some_classes.ConnectionToDataBase;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Message {
    private int message_id;
    private int user_id;
    private String text;
    private Date datetime;

    private String insertString = "INSERT INTO java_lab_HW.Message (user_id, text, datetime)" + "VALUES (?, ?, ?)";

    public Message(int user_id, String text, Date datetime) {
        this.user_id = user_id;
        this.text = text;
        this.datetime = datetime;
    }

    public void addToDB() {
        try {

            PreparedStatement preparedStatement = ConnectionToDataBase.getConnection().prepareStatement(insertString);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, text);
            preparedStatement.setDate(3, datetime);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("adding to data base failed");
        }
    }
}

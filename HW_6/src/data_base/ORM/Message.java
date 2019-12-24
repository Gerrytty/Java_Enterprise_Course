package data_base.ORM;

import java.sql.Date;

public class Message {
    private int message_id;
    private int user_id;
    private String text;
    private Date datetime;

    public Message(int user_id, String text, Date datetime) {
        this.user_id = user_id;
        this.text = text;
        this.datetime = datetime;
    }

    public Message() {

    }

    public int getMessage_id() {
        return message_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getText() {
        return text;
    }

    public Date getDatetime() {
        return datetime;
    }


    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}

package model;

import java.sql.Date;

public class Message {

    private int id;
    private int userId;
    private String text;
    private Date datetime;

    public Message(int id, String text, int userId, Date datetime) {
        this(id, text);
        this.userId = userId;
        this.datetime = datetime;
    }

    public Message(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public Message() {

    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

}

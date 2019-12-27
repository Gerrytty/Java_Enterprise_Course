package model;

public class User {

    private int id;
    private String login;
    private String password;
    private int role;
    private String token;

    public User() {

    }

    public User(String login, String password, int role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(int id, String login, String password, int role) {
        this(login, password, role);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

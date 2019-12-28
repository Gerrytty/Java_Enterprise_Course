package dto;

import model.User;

public class UserDto implements Dto {
    private int id;
    private String name;
    private String token;

    public UserDto(int id, String name, String token) {
        this.id = id;
        this.name = name;
        this.token = token;
    }

    public UserDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static UserDto from(User user) {
        return new UserDto(user.getId(), user.getPassword(), user.getToken());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}



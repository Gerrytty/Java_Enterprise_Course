package dto;

public class MessageDto implements Dto{
    private String message;
    private String username;

    public MessageDto() {
    }

    public MessageDto(String username, String message) {
        this.username = username;
        this.message = message;
    }

    public MessageDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

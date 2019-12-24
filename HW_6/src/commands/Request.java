package commands;

public class Request<T> {
    private String header;
    private T payload;
    private String token;

    public Request() {

    }

    public Request(String header, T payload) {
        this.header = header;
        this.payload = payload;
    }

    public Request(String header, T payload, String token) {
        this(header, payload);
        this.token = token;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    public String getHeader() {
        return header;
    }

    public T getPayload() {
        return payload;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

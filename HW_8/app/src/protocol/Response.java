package protocol;

public class Response<T> {
    private T data;

    public Response() {

    }

    private Response(T data) {
        this.data = data;
    }

    public static <E> Response<E> build(E data) {
        return new Response<>(data);
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

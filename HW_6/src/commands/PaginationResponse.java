package commands;

import java.util.List;

public class PaginationResponse<T> {

    private List<T> data;

    public PaginationResponse() {
    }

    public PaginationResponse(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
package commands;

import ORM.Message;

import java.util.List;

public class PaginationResponse {

    private List<ORM.Message> data;

    public PaginationResponse() {
    }

    public PaginationResponse(List<ORM.Message> data) {
        this.data = data;
    }

    public List<ORM.Message> getData() {
        return data;
    }

    public void setData(List<Message> data) {
        this.data = data;
    }
}
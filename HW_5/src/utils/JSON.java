package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSON<T> {
    private ObjectMapper mapper;

    public JSON() {
        mapper = new ObjectMapper();
    }

    public String createJSON(T t) {

        ObjectMapper mapper = new ObjectMapper();

        String jsonValue = null;

        try {
            jsonValue = mapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonValue;
    }

}

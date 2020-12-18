import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Form {
    private String action;
    private String method;
    private List<Input> inputs;

    public Form(String action, String method) {
        this.action = action;
        this.method = method;
        inputs = new ArrayList<>();
    }

}
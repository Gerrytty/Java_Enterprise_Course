import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {

    private String name;
    private String secondName;
    private String date;
    private String age;
    private String passportNumber;

}

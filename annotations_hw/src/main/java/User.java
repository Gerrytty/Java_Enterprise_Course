@HtmlForm(method = "post", action = "/user")
public class User {
    @HtmlInput(name = "first_name", placeholder = "Имя")
    private String firstName;
    @HtmlInput(name = "last_name", placeholder = "Фамилия")
    private String lastName;
    @HtmlInput(name = "email", placeholder = "Email")
    private String email;
    @HtmlInput(name = "password", placeholder = "Пароль")
    private String password;
}
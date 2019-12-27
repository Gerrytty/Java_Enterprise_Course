import repositories.user.UsersRepositoryImpl;
import services.login.LoginServiceImpl;

public class MainTest {
    public static void main(String[] args) {

        LoginServiceImpl loginService = new LoginServiceImpl(new UsersRepositoryImpl());
        loginService.signIn("login", "123");

    }
}

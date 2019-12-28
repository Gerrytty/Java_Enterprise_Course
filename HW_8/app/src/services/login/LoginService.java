package services.login;

import dto.UserDto;

public interface LoginService {

    UserDto signIn(String login, String password);

    UserDto signUp(String login, String password);
}

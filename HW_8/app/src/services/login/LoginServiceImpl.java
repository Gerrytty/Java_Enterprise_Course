package services.login;

import context.Component;
import dto.UserDto;
import model.User;
import repositories.user.UsersRepository;
import repositories.user.UsersRepositoryImpl;
import utils.Password;
import utils.TokenUtil;

import java.util.Optional;

import static dto.UserDto.from;

public class LoginServiceImpl implements LoginService, Component {

    private UsersRepository usersRepository;

    public LoginServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public LoginServiceImpl() {
        usersRepository = new UsersRepositoryImpl();
    }

    /***
     * @param login
     * @param password
     * @return instance of UserDto if user is exists and password is correct else null
     */
    public UserDto signIn(String login, String password) {

      Optional<User> userCandidate = usersRepository.findByLogin(login);

      if (userCandidate.isPresent()) {
          User user = userCandidate.get();

          if (Password.check(password, user.getPassword())) {
              user.setToken(TokenUtil.createToken(user.getId(), true));
              return from(user);
          }

          else {
              System.out.println("Wrong password");
              return null;
          }
      }

      else {
          System.out.println("User is not exists");
          return null;
      }

    }

    /***
     * @param login
     * @param password
     * @return instance of UserDto if user is not exists else null
     */
    public UserDto signUp(String login, String password) {

        Optional<User> userCandidate = usersRepository.findByLogin(login);

        if(!userCandidate.isPresent()) {

            usersRepository.save(new User(login, Password.hash(password), 0));
            User newUser = usersRepository.findByLogin(login).get();
            newUser.setToken(TokenUtil.createToken(newUser.getId(), newUser.getRole() == 1));

            return from(newUser);

        }

        else {
            return null;
        }

    }

    @Override
    public String getName() {
        return "loginService";
    }
}

package repositories.user;

import model.User;
import repositories.CrudRepository;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}

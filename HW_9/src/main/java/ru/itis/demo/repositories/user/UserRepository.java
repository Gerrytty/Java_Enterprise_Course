package ru.itis.demo.repositories.user;

import ru.itis.demo.model.NetflixUser;
import ru.itis.demo.repositories.CrudRepository;

public interface UserRepository extends CrudRepository<NetflixUser, Long> {

}

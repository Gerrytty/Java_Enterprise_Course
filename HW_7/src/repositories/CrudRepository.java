package repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T,ID> {

    Optional<T> findOne(ID id);
    List<T> findAll();

    void save(T t);
    void delete(int id);
}

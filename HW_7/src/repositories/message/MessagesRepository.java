package repositories.message;

import dto.MessageDto;
import model.Message;
import repositories.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MessagesRepository extends CrudRepository<Message, Integer> {

    Optional<List<MessageDto>> findByPage(int page);

}

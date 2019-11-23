package repositories;

import models.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Long> {
    List<Message> messagesBetweenTwoPerson(Long firstId, Long secondId);

    List<Long> senderIDsTo(Long id);
}

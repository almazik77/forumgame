package services;

import models.Message;
import repositories.MessageRepository;

import java.util.List;

public class MessageService {
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(String message, Long from, Long to) {
        messageRepository.save(Message.builder().message(message).fromUserId(from).toUserId(to).build());
    }

    public List<Message> messagesBetweenTwoPerson(Long first, Long second) {
        return messageRepository.messagesBetweenTwoPerson(first, second);
    }
}

package services.message;

import context.Component;
import dto.Dto;
import model.Message;
import repositories.message.MessagesRepository;

public class AddMessageServiceImpl implements AddMessageService, Component {

    private MessagesRepository messagesRepository;

    public AddMessageServiceImpl(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    @Override
    public Dto save(String message, int id) {
        messagesRepository.save(new Message(id, message));
        return null;
    }

    @Override
    public String getName() {
        return "addMessageService";
    }
}
package ru.javaschool.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaschool.chat.entities.Message;
import ru.javaschool.chat.repositories.ChatRepository;
import ru.javaschool.chat.repositories.MessageRepository;
import ru.javaschool.chat.repositories.PeopleRepository;
import ru.javaschool.chat.utils.MessageRequest;
import ru.javaschool.chat.utils.MessageResponse;
import ru.javaschool.chat.utils.MessageStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class Service {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    public MessageResponse sendMessage(MessageRequest rawMessage) {
        MessageResponse messageResponse = new MessageResponse();
        List<MessageStatus> statuses = validate(rawMessage);
        //если нет ошибок пытается сохранить сообщение
        if (statuses.size() == 0) {
            Message message = createMessage(rawMessage);
            try {
                Message saveMessage = messageRepository.save(message);
                messageResponse.setMessageID(saveMessage.getId());
                statuses.add(MessageStatus.SUCCESS);
            } catch (Exception e) {
                statuses.add(MessageStatus.FAILEDTOSAVE);
                //здесь должен быть логгер
                e.printStackTrace();
            }
        }
        messageResponse.setStatuses(statuses);
        return messageResponse;
    }

    private List<MessageStatus> validate(MessageRequest rawMessage) {
        List<MessageStatus> statuses = new ArrayList<>();
        if (!peopleRepository.existsById(rawMessage.getAuthorID())) {
            statuses.add(MessageStatus.AUTHORNOTFOUND);
        }
        if (!chatRepository.existsById(rawMessage.getChatID())) {
            statuses.add(MessageStatus.CHATNOTFOUND);
        } else if (chatRepository.getOne(rawMessage.getChatID()).isClosed()) {
            statuses.add(MessageStatus.CHATISCLOSED);
        }
        if (rawMessage.getTextMessage().trim().length() == 0) {
            statuses.add(MessageStatus.MESSAGEISEMPTY);
        }
        return statuses;
    }

    private Message createMessage(MessageRequest rawMessage) {
        Message message = new Message();
        message.setChat(chatRepository.getOne(rawMessage.getChatID()));
        message.setPerson(peopleRepository.getOne(rawMessage.getAuthorID()));
        message.setMessageText(rawMessage.getTextMessage());
        message.setMessageTime(LocalDateTime.now());
        return message;
    }

    public List<Message> getMessagesFromCurrent(long chatId) {
        return messageRepository.findBychat_id(chatId);
    }

    public List<Message> getMessagesFromClosed() {
        return messageRepository.findBychat_closed(true);
    }
}

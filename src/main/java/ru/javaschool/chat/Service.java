package ru.javaschool.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.javaschool.chat.entities.Message;
import ru.javaschool.chat.repository.ChatRepository;
import ru.javaschool.chat.repository.MessageRepository;
import ru.javaschool.chat.repository.PeopleRepository;

import java.util.List;

@Component
public class Service {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PeopleRepository peopleRepository;

    public List<Message> getMessagesFromCurrent(int chatId){
        return messageRepository.findBychat_id(chatId);
    }

    public List<Message> getMessagesFromClosed(){
        return messageRepository.findBychat_closed(true);
    }
}

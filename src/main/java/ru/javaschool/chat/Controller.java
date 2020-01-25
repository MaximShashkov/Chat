package ru.javaschool.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.javaschool.chat.entities.Message;
import java.util.List;

@RestController
public class Controller {
@Autowired
    Service service;

    @GetMapping("getMessagesFromCurrent")
    public List<Message> getMessagesFromCurrent(@RequestParam int chatId){
        return service.messageRepository.findBychat_id(chatId);
    }

    @GetMapping("getMessagesFromClosed")
    public List<Message> getMessagesFromClosed(){
        return service.getMessagesFromClosed();
    }
}

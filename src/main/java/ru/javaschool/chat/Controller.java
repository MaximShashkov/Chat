package ru.javaschool.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.javaschool.chat.entities.Message;
import ru.javaschool.chat.utils.MessageRequest;
import ru.javaschool.chat.utils.MessageResponse;


import java.util.List;

@RestController
public class Controller {
@Autowired
    Service service;

    @PostMapping("sendMessage")
    public MessageResponse sendMessage(@RequestBody MessageRequest rawMessage){
        return service.sendMessage(rawMessage);
    }

    @GetMapping("getMessagesFromCurrent")
    public List<Message> getMessagesFromCurrent(@RequestParam int chatId){
        return service.getMessagesFromCurrent(chatId);
    }

    @GetMapping("getMessagesFromClosed")
    public List<Message> getMessagesFromClosed(){
        return service.getMessagesFromClosed();
    }
}

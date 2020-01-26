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
    private Service service;

    @PostMapping(value = "sendMessage", consumes = "application/json", produces = "application/json")
    public MessageResponse sendMessage(@RequestBody MessageRequest rawMessage) {
        return service.sendMessage(rawMessage);
    }

    @GetMapping(value = "getMessagesFromCurrent", produces = "application/json")
    public List<Message> getMessagesFromCurrent(@RequestParam long chatId) {
        return service.getMessagesFromCurrent(chatId);
    }

    @GetMapping(value = "getMessagesFromClosed", produces = "application/json")
    public List<Message> getMessagesFromClosed() {
        return service.getMessagesFromClosed();
    }
}

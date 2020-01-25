package ru.javaschool.chat.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name="Messages")
@Entity
@Component
public class Message {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
   private long id;

    @ManyToOne
    private Person person;

    @ManyToOne
    private Chat chat;

    @Column
    private LocalDateTime messageTime;

    @Column
    private String messageText;

    public Message() {
    }

    public Message(long id, Person person, Chat chat, LocalDateTime messageTime, String messageText) {
        this.id = id;
        this.person = person;
        this.chat = chat;
        this.messageTime = messageTime;
        this.messageText = messageText;
    }

    public long getId() {
        return id;
    }

    public Person getPerson() {
        return person;
    }

    public Chat getChat() {
        return chat;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public String getMessageText() {
        return messageText;
    }
}

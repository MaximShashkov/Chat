package ru.javaschool.chat.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Table(name="Chats")
@Entity
@Component
public class Chat {

    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;

    @Column
    boolean closed;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

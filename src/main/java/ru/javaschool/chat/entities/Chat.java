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
    int id;

    @Column
    boolean closed;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}

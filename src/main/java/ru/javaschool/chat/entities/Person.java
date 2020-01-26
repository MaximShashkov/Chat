package ru.javaschool.chat.entities;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "People")
@Entity
@Component
public class Person {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;

    @Column
    String nickName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}

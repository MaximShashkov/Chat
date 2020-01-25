package ru.javaschool.chat.entities;

import org.springframework.stereotype.Component;
import javax.persistence.*;

@Table(name="People")
@Entity
@Component
public class Person {
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    long id;

    @Column
    String nickName;

}

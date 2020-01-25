package ru.javaschool.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaschool.chat.entities.Person;

public interface PeopleRepository extends JpaRepository<Person, Long> {
}

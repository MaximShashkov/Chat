package ru.javaschool.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javaschool.chat.entities.Person;

public interface PeopleRepository extends CrudRepository<Person, Long> {
}

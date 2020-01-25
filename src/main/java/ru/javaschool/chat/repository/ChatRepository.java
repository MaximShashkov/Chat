package ru.javaschool.chat.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javaschool.chat.entities.Chat;

public interface ChatRepository extends CrudRepository<Chat, Long> {
}

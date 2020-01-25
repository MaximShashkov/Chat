package ru.javaschool.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaschool.chat.entities.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}

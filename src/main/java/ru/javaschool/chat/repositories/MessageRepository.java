package ru.javaschool.chat.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javaschool.chat.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findBychat_id(long id);

    public List<Message> findBychat_closed(boolean isClosed);
}

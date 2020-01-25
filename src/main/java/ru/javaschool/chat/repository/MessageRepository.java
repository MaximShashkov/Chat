package ru.javaschool.chat.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.javaschool.chat.entities.Message;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    public List<Message> findBychat_closed(boolean isClosed);
}

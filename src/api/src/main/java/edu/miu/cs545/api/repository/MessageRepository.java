package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findMessagesByRecipientIdOrderByDateDescTimeDesc(Long id);
    List<Message> findMessagesByRecipientIdAndPropertyIdOrderByDateDescTimeDesc(Long userId, Long propertyId);
}

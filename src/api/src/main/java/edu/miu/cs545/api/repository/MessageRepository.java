package edu.miu.cs545.api.repository;

import edu.miu.cs545.api.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("SELECT m FROM Message m OUTER JOIN m.recipient r OUTER JOIN m.sender s WHERE r.id = :id OR s.id = :id ORDER BY m.date DESC, m.time DESC")
    List<Message> findMessagesByRecipientIdOrderByDateDescTimeDesc(Long id);
    @Query("SELECT m FROM Message m OUTER JOIN m.recipient r OUTER JOIN m.sender s JOIN m.property p WHERE (r.id = :userId OR s.id = :userId) AND p.id = :propertyId ORDER BY m.date DESC, m.time DESC")
    List<Message> findMessagesByRecipientIdAndPropertyIdOrderByDateDescTimeDesc(Long userId, Long propertyId);
}

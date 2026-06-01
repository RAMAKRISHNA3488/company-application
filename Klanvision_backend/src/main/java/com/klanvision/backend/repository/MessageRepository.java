package com.klanvision.backend.repository;

import com.klanvision.backend.model.AdminMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<AdminMessage, Long> {
    List<AdminMessage> findAllByRecipientIdOrderByTimestampDesc(Long recipientId);
    List<AdminMessage> findAllBySenderIdOrderByTimestampDesc(Long senderId);

    @org.springframework.transaction.annotation.Transactional
    void deleteAllByTimestampBefore(java.time.LocalDateTime timestamp);
}

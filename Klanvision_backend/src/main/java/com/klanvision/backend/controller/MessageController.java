package com.klanvision.backend.controller;

import com.klanvision.backend.model.AdminMessage;
import com.klanvision.backend.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
@CrossOrigin(origins = "*")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    @GetMapping
    public List<AdminMessage> getAllMessages() {
        // Return messages in chronological order (ascending by timestamp)
        return messageRepository.findAll(org.springframework.data.domain.Sort.by(org.springframework.data.domain.Sort.Direction.ASC, "timestamp"));
    }

    @PostMapping
    public AdminMessage sendMessage(@RequestBody AdminMessage message) {
        message.setTimestamp(LocalDateTime.now());
        return messageRepository.save(message);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMessage(@PathVariable Long id) {
        messageRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // Cron job running daily at midnight to purge chat messages older than 30 days
    @Scheduled(cron = "0 0 0 * * ?")
    public void purgeOldMessages() {
        LocalDateTime limit = LocalDateTime.now().minusDays(30);
        messageRepository.deleteAllByTimestampBefore(limit);
        System.out.println("[Scheduler] Cleared messages older than 30 days (before " + limit + ").");
    }
}

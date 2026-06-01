package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long senderId;

    @com.fasterxml.jackson.annotation.JsonProperty("receiverId")
    private Long recipientId;
    
    @Column(columnDefinition = "TEXT")
    @com.fasterxml.jackson.annotation.JsonProperty("text")
    private String content;
    
    private LocalDateTime timestamp = LocalDateTime.now();
    @com.fasterxml.jackson.annotation.JsonProperty("isRead")
    private boolean isRead = false;

    @Column(columnDefinition = "LONGTEXT")
    private String attachment;

    private Long replyToId;
}

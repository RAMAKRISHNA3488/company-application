package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String phone;
    private String dob;
    private String gender;
    private String qualification;
    private String experience;

    @Column(columnDefinition = "TEXT")
    private String skills;

    private String linkedin;
    private String portfolio;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] resumeData;
    
    private String resumeFileName;
    private String resumeContentType;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}

package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String jobTitle;
    private String name;
    private String dob;
    private String email;
    private String phone;
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

    private LocalDateTime submittedAt;

    @PrePersist
    protected void onCreate() {
        submittedAt = LocalDateTime.now();
    }
}

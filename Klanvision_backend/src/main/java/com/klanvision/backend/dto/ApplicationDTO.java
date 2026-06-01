package com.klanvision.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationDTO {
    private Long id;
    private String jobTitle;
    private String name;
    private String dob;
    private String email;
    private String phone;
    private String gender;
    private String qualification;
    private String experience;
    private String skills;
    private String linkedin;
    private String portfolio;
    private String resumeFileName;
    private LocalDateTime submittedAt;
}

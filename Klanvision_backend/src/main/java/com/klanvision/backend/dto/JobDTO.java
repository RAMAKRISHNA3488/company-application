package com.klanvision.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobDTO {
    private Long id;
    private String title;
    private String department;
    private String location;
    private String type;
    private String description;
    private String requirements;
    private boolean active;
}

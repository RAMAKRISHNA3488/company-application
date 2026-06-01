package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@Table(name = "admins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password; 
    
    private String role = "ADMIN";
    private String status = "Offline";
    private String lastActive = "Never";
    private String color = "#6366F1";

    @ElementCollection
    private List<String> permissions;

    @com.fasterxml.jackson.annotation.JsonProperty("isAuthorized")
    private boolean isAuthorized = true;

    @com.fasterxml.jackson.annotation.JsonProperty("is2FAEnabled")
    private boolean is2FAEnabled = false;

    @com.fasterxml.jackson.annotation.JsonProperty("is2FAConfigured")
    private boolean is2FAConfigured = false;
    private String secret2FA;
    private int failed2FAAttempts = 0;
}

package com.klanvision.backend.controller;

import com.klanvision.backend.model.AdminUser;
import com.klanvision.backend.payload.request.LoginRequest;
import com.klanvision.backend.payload.response.LoginResponse;
import com.klanvision.backend.payload.response.TwoFAResponse;
import com.klanvision.backend.service.AdminService;
import dev.samstevens.totp.exceptions.QrGenerationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = adminService.login(loginRequest);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @PostMapping("/verify-2fa")
    public ResponseEntity<?> verify2FA(@RequestParam String usernameOrEmail, @RequestParam String code) {
        AdminUser admin = adminService.verify2FA(usernameOrEmail, code);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid 2FA code");
    }

    @GetMapping("/generate-2fa")
    public ResponseEntity<?> generate2FA(@RequestParam String usernameOrEmail) throws QrGenerationException {
        TwoFAResponse response = adminService.generate2FA(usernameOrEmail);
        if (response != null) {
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
    }

    @PostMapping("/setup")
    public ResponseEntity<?> setupAdmin(@RequestBody AdminUser admin) {
        AdminUser created = adminService.setupAdmin(admin);
        if (created != null) {
            return ResponseEntity.ok(created);
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin already exists or setup failed");
    }

    // User Management
    @GetMapping("/users")
    public java.util.List<AdminUser> getAllUsers() {
        return adminService.getAllAdmins();
    }

    @PostMapping("/users")
    public AdminUser createUser(@RequestBody AdminUser user) {
        return adminService.saveAdmin(user);
    }

    @PutMapping("/users/{id}")
    public AdminUser updateUser(@PathVariable Long id, @RequestBody AdminUser user) {
        user.setId(id);
        return adminService.saveAdmin(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        return ResponseEntity.ok().build();
    }
}

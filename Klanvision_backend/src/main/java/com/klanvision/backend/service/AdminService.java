package com.klanvision.backend.service;

import com.klanvision.backend.model.AdminUser;
import com.klanvision.backend.payload.request.LoginRequest;
import com.klanvision.backend.payload.response.LoginResponse;
import com.klanvision.backend.payload.response.TwoFAResponse;
import dev.samstevens.totp.exceptions.QrGenerationException;

public interface AdminService {
    LoginResponse login(LoginRequest loginRequest);
    AdminUser verify2FA(String usernameOrEmail, String code);
    TwoFAResponse generate2FA(String usernameOrEmail) throws QrGenerationException;
    AdminUser setupAdmin(AdminUser admin);
    
    // User Management
    java.util.List<AdminUser> getAllAdmins();
    AdminUser saveAdmin(AdminUser admin);
    void deleteAdmin(Long id);
    AdminUser getAdminById(Long id);
}

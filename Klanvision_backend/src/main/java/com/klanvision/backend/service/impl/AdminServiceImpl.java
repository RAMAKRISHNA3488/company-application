package com.klanvision.backend.service.impl;

import com.klanvision.backend.model.AdminUser;
import com.klanvision.backend.payload.request.LoginRequest;
import com.klanvision.backend.payload.response.LoginResponse;
import com.klanvision.backend.payload.response.TwoFAResponse;
import com.klanvision.backend.repository.AdminUserRepository;
import com.klanvision.backend.service.AdminService;
import dev.samstevens.totp.code.CodeGenerator;
import dev.samstevens.totp.code.CodeVerifier;
import dev.samstevens.totp.code.DefaultCodeGenerator;
import dev.samstevens.totp.code.DefaultCodeVerifier;
import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.exceptions.QrGenerationException;
import dev.samstevens.totp.qr.QrData;
import dev.samstevens.totp.qr.QrGenerator;
import dev.samstevens.totp.qr.ZxingPngQrGenerator;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.SystemTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

import static dev.samstevens.totp.util.Utils.getDataUriForImage;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        Optional<AdminUser> admin = adminUserRepository.findByUsernameOrEmail(
            loginRequest.getUsernameOrEmail(), 
            loginRequest.getUsernameOrEmail()
        );
        if (admin.isPresent() && admin.get().getPassword().equals(loginRequest.getPassword())) {
            return new LoginResponse(
                admin.get().getEmail(),
                admin.get().getRole(),
                admin.get().is2FAEnabled(),
                "Login successful"
            );
        }
        return null;
    }

    @Override
    public AdminUser verify2FA(String usernameOrEmail, String code) {
        Optional<AdminUser> admin = adminUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (admin.isPresent()) {
            TimeProvider timeProvider = new SystemTimeProvider();
            CodeGenerator codeGenerator = new DefaultCodeGenerator();
            CodeVerifier verifier = new DefaultCodeVerifier(codeGenerator, timeProvider);

            if (verifier.isValidCode(admin.get().getSecret2FA(), code)) {
                return admin.get();
            }
        }
        return null;
    }

    @Override
    public TwoFAResponse generate2FA(String usernameOrEmail) throws QrGenerationException {
        Optional<AdminUser> admin = adminUserRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (admin.isPresent()) {
            SecretGenerator secretGenerator = new DefaultSecretGenerator();
            String secret = secretGenerator.generate();
            
            admin.get().setSecret2FA(secret);
            adminUserRepository.save(admin.get());

            QrData data = new QrData.Builder()
                    .label(admin.get().getEmail())
                    .secret(secret)
                    .issuer("Klanvision")
                    .algorithm(HashingAlgorithm.SHA1)
                    .digits(6)
                    .period(30)
                    .build();

            QrGenerator generator = new ZxingPngQrGenerator();
            byte[] imageData = generator.generate(data);
            String mimeType = generator.getImageMimeType();

            return new TwoFAResponse(secret, getDataUriForImage(imageData, mimeType));
        }
        return null;
    }

    @Override
    public AdminUser setupAdmin(AdminUser admin) {
        if (adminUserRepository.count() == 0) {
            return adminUserRepository.save(admin);
        }
        return null;
    }

    @Override
    public java.util.List<AdminUser> getAllAdmins() {
        return adminUserRepository.findAll();
    }

    @Override
    public AdminUser saveAdmin(AdminUser admin) {
        return adminUserRepository.save(admin);
    }

    @Override
    public void deleteAdmin(Long id) {
        adminUserRepository.deleteById(id);
    }

    @Override
    public AdminUser getAdminById(Long id) {
        return adminUserRepository.findById(id).orElse(null);
    }
}

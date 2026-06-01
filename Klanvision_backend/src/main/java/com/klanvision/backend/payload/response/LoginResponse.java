package com.klanvision.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private String email;
    private String role;
    private boolean requires2FA;
    private String message;
}

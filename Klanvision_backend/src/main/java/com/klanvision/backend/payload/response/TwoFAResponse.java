package com.klanvision.backend.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TwoFAResponse {
    private String secret;
    private String qrCode;
}

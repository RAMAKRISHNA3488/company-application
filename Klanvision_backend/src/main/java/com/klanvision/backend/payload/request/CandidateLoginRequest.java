package com.klanvision.backend.payload.request;

import lombok.Data;

@Data
public class CandidateLoginRequest {
    private String email;
    private String password;
}

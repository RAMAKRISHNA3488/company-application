package com.klanvision.backend.service;

import com.klanvision.backend.dto.CandidateDTO;
import com.klanvision.backend.model.Candidate;
import com.klanvision.backend.payload.request.CandidateLoginRequest;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface CandidateService {
    CandidateDTO register(Candidate candidate, MultipartFile resume) throws IOException;
    CandidateDTO login(CandidateLoginRequest loginRequest);
    CandidateDTO getCandidateById(Long id);
    Candidate getCandidateEntity(Long id);
    boolean existsByEmail(String email);
}

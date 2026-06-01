package com.klanvision.backend.service.impl;

import com.klanvision.backend.dto.CandidateDTO;
import com.klanvision.backend.model.Candidate;
import com.klanvision.backend.payload.request.CandidateLoginRequest;
import com.klanvision.backend.repository.CandidateRepository;
import com.klanvision.backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Optional;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    private CandidateRepository candidateRepository;

    @Override
    public CandidateDTO register(Candidate candidate, MultipartFile resume) throws IOException {
        if (resume != null && !resume.isEmpty()) {
            candidate.setResumeData(resume.getBytes());
            candidate.setResumeFileName(resume.getOriginalFilename());
            candidate.setResumeContentType(resume.getContentType());
        }
        return mapToDTO(candidateRepository.save(candidate));
    }

    @Override
    public CandidateDTO login(CandidateLoginRequest loginRequest) {
        Optional<Candidate> candidate = candidateRepository.findByEmail(loginRequest.getEmail());
        if (candidate.isPresent() && candidate.get().getPassword().equals(loginRequest.getPassword())) {
            return mapToDTO(candidate.get());
        }
        return null;
    }

    @Override
    public CandidateDTO getCandidateById(Long id) {
        return candidateRepository.findById(id).map(this::mapToDTO).orElse(null);
    }

    @Override
    public Candidate getCandidateEntity(Long id) {
        return candidateRepository.findById(id).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return candidateRepository.existsByEmail(email);
    }

    private CandidateDTO mapToDTO(Candidate c) {
        return new CandidateDTO(
            c.getId(), c.getName(), c.getEmail(), c.getPhone(),
            c.getDob(), c.getGender(), c.getQualification(),
            c.getExperience(), c.getSkills(), c.getLinkedin(),
            c.getPortfolio(), c.getResumeFileName(), c.getCreatedAt()
        );
    }
}

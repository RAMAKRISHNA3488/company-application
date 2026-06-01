package com.klanvision.backend.controller;

import com.klanvision.backend.dto.CandidateDTO;
import com.klanvision.backend.model.Candidate;
import com.klanvision.backend.payload.request.CandidateLoginRequest;
import com.klanvision.backend.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin(origins = "*")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @PostMapping(value = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "dob", required = false) String dob,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "qualification", required = false) String qualification,
            @RequestParam(value = "experience", required = false) String experience,
            @RequestParam(value = "skills", required = false) String skills,
            @RequestParam(value = "linkedin", required = false) String linkedin,
            @RequestParam(value = "portfolio", required = false) String portfolio,
            @RequestParam(value = "resume", required = false) MultipartFile resume) {

        if (candidateService.existsByEmail(email)) {
            return ResponseEntity.badRequest().body("Email already registered");
        }

        try {
            Candidate candidate = new Candidate();
            candidate.setName(name);
            candidate.setEmail(email);
            candidate.setPassword(password);
            candidate.setPhone(phone);
            candidate.setDob(dob);
            candidate.setGender(gender);
            candidate.setQualification(qualification);
            candidate.setExperience(experience);
            candidate.setSkills(skills);
            candidate.setLinkedin(linkedin);
            candidate.setPortfolio(portfolio);

            CandidateDTO created = candidateService.register(candidate, resume);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing resume");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody CandidateLoginRequest loginRequest) {
        CandidateDTO candidate = candidateService.login(loginRequest);
        if (candidate != null) {
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("token", "mock-candidate-token-" + candidate.getId());
            response.put("candidate", candidate);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
    }

    @GetMapping("/{id}")
    public ResponseEntity<CandidateDTO> getProfile(@PathVariable Long id) {
        CandidateDTO candidate = candidateService.getCandidateById(id);
        if (candidate != null) {
            return ResponseEntity.ok(candidate);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> downloadResume(@PathVariable Long id) {
        Candidate candidate = candidateService.getCandidateEntity(id);
        if (candidate != null && candidate.getResumeData() != null) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + candidate.getResumeFileName() + "\"")
                    .contentType(MediaType.parseMediaType(candidate.getResumeContentType()))
                    .body(candidate.getResumeData());
        }
        return ResponseEntity.notFound().build();
    }
}

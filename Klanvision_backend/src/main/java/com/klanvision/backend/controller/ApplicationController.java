package com.klanvision.backend.controller;

import com.klanvision.backend.dto.ApplicationDTO;
import com.klanvision.backend.model.Application;
import com.klanvision.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/applications")
@CrossOrigin(origins = "*")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitApplication(
            @RequestParam("jobTitle") String jobTitle,
            @RequestParam("name") String name,
            @RequestParam("dob") String dob,
            @RequestParam("email") String email,
            @RequestParam("phone") String phone,
            @RequestParam("gender") String gender,
            @RequestParam("qualification") String qualification,
            @RequestParam("experience") String experience,
            @RequestParam("skills") String skills,
            @RequestParam(value = "linkedin", required = false) String linkedin,
            @RequestParam(value = "portfolio", required = false) String portfolio,
            @RequestParam("resume") MultipartFile resume) {

        try {
            ApplicationDTO dto = new ApplicationDTO();
            dto.setJobTitle(jobTitle);
            dto.setName(name);
            dto.setDob(dob);
            dto.setEmail(email);
            dto.setPhone(phone);
            dto.setGender(gender);
            dto.setQualification(qualification);
            dto.setExperience(experience);
            dto.setSkills(skills);
            dto.setLinkedin(linkedin);
            dto.setPortfolio(portfolio);

            applicationService.submitApplication(dto, resume);
            return ResponseEntity.ok("Application submitted successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing resume");
        }
    }

    @GetMapping
    public List<ApplicationDTO> getAllApplications() {
        return applicationService.getAllApplications();
    }

    @GetMapping("/resume/{id}")
    public ResponseEntity<byte[]> getResume(@PathVariable Long id) {
        Application app = applicationService.getApplicationEntity(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + app.getResumeFileName() + "\"")
                .contentType(MediaType.parseMediaType(app.getResumeContentType()))
                .body(app.getResumeData());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApplication(@PathVariable Long id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.ok("Application deleted");
    }
}

package com.klanvision.backend.service.impl;

import com.klanvision.backend.dto.ApplicationDTO;
import com.klanvision.backend.model.Application;
import com.klanvision.backend.repository.ApplicationRepository;
import com.klanvision.backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public void submitApplication(ApplicationDTO dto, MultipartFile resume) throws IOException {
        Application app = new Application();
        app.setJobTitle(dto.getJobTitle());
        app.setName(dto.getName());
        app.setDob(dto.getDob());
        app.setEmail(dto.getEmail());
        app.setPhone(dto.getPhone());
        app.setGender(dto.getGender());
        app.setQualification(dto.getQualification());
        app.setExperience(dto.getExperience());
        app.setSkills(dto.getSkills());
        app.setLinkedin(dto.getLinkedin());
        app.setPortfolio(dto.getPortfolio());
        
        app.setResumeData(resume.getBytes());
        app.setResumeFileName(resume.getOriginalFilename());
        app.setResumeContentType(resume.getContentType());

        applicationRepository.save(app);
    }

    @Override
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Application getApplicationEntity(Long id) {
        return applicationRepository.findById(id).orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public void deleteApplication(Long id) {
        applicationRepository.deleteById(id);
    }

    private ApplicationDTO mapToDTO(Application app) {
        return new ApplicationDTO(
                app.getId(),
                app.getJobTitle(),
                app.getName(),
                app.getDob(),
                app.getEmail(),
                app.getPhone(),
                app.getGender(),
                app.getQualification(),
                app.getExperience(),
                app.getSkills(),
                app.getLinkedin(),
                app.getPortfolio(),
                app.getResumeFileName(),
                app.getSubmittedAt()
        );
    }
}

package com.klanvision.backend.service;

import com.klanvision.backend.dto.ApplicationDTO;
import com.klanvision.backend.model.Application;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface ApplicationService {
    void submitApplication(ApplicationDTO applicationDTO, MultipartFile resume) throws IOException;
    List<ApplicationDTO> getAllApplications();
    Application getApplicationEntity(Long id);
    void deleteApplication(Long id);
}

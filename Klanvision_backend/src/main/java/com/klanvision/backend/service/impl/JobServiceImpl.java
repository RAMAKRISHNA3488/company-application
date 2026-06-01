package com.klanvision.backend.service.impl;

import com.klanvision.backend.dto.JobDTO;
import com.klanvision.backend.model.JobListing;
import com.klanvision.backend.repository.JobListingRepository;
import com.klanvision.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private JobListingRepository jobListingRepository;

    @Override
    public List<JobDTO> getActiveJobs() {
        return jobListingRepository.findByActiveTrue().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public List<JobDTO> getAllJobs() {
        return jobListingRepository.findAll().stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public JobDTO createJob(JobDTO dto) {
        JobListing job = mapToEntity(dto);
        return mapToDTO(jobListingRepository.save(job));
    }

    @Override
    public JobDTO updateJob(Long id, JobDTO dto) {
        JobListing job = jobListingRepository.findById(id).orElseThrow();
        job.setTitle(dto.getTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setType(dto.getType());
        job.setDescription(dto.getDescription());
        job.setRequirements(dto.getRequirements());
        job.setActive(dto.isActive());
        return mapToDTO(jobListingRepository.save(job));
    }

    @Override
    public void deleteJob(Long id) {
        jobListingRepository.deleteById(id);
    }

    private JobDTO mapToDTO(JobListing job) {
        return new JobDTO(
            job.getId(), job.getTitle(), job.getDepartment(),
            job.getLocation(), job.getType(), job.getDescription(),
            job.getRequirements(), job.isActive()
        );
    }

    private JobListing mapToEntity(JobDTO dto) {
        JobListing job = new JobListing();
        job.setTitle(dto.getTitle());
        job.setDepartment(dto.getDepartment());
        job.setLocation(dto.getLocation());
        job.setType(dto.getType());
        job.setDescription(dto.getDescription());
        job.setRequirements(dto.getRequirements());
        job.setActive(dto.isActive());
        return job;
    }
}

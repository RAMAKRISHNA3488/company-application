package com.klanvision.backend.service;

import com.klanvision.backend.dto.JobDTO;
import java.util.List;

public interface JobService {
    List<JobDTO> getActiveJobs();
    List<JobDTO> getAllJobs();
    JobDTO createJob(JobDTO jobDTO);
    JobDTO updateJob(Long id, JobDTO jobDTO);
    void deleteJob(Long id);
}

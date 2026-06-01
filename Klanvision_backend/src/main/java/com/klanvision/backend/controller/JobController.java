package com.klanvision.backend.controller;

import com.klanvision.backend.dto.JobDTO;
import com.klanvision.backend.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@CrossOrigin(origins = "*")
public class JobController {

    @Autowired
    private JobService jobService;

    @GetMapping
    public List<JobDTO> getActiveJobs() {
        return jobService.getActiveJobs();
    }

    @GetMapping("/all")
    public List<JobDTO> getAllJobs() {
        return jobService.getAllJobs();
    }

    @PostMapping
    public JobDTO createJob(@RequestBody JobDTO jobDTO) {
        return jobService.createJob(jobDTO);
    }

    @PutMapping("/{id}")
    public JobDTO updateJob(@PathVariable Long id, @RequestBody JobDTO jobDTO) {
        return jobService.updateJob(id, jobDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok("Job deleted");
    }
}

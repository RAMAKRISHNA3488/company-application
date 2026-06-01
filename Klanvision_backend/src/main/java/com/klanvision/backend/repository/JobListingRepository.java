package com.klanvision.backend.repository;

import com.klanvision.backend.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface JobListingRepository extends JpaRepository<JobListing, Long> {
    List<JobListing> findByActiveTrue();
}

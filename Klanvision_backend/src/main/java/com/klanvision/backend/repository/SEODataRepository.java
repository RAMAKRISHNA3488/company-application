package com.klanvision.backend.repository;

import com.klanvision.backend.model.SEOData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SEODataRepository extends JpaRepository<SEOData, Long> {
}

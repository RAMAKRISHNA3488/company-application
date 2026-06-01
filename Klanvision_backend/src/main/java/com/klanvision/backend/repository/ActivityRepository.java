package com.klanvision.backend.repository;

import com.klanvision.backend.model.AuditActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<AuditActivity, Long> {
    List<AuditActivity> findAllByOrderByTimestampDesc();
}

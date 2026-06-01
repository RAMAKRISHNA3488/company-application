package com.klanvision.backend.repository;

import com.klanvision.backend.model.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findByEmail(String email);
    Optional<AdminUser> findByUsername(String username);
    Optional<AdminUser> findByUsernameOrEmail(String username, String email);
}

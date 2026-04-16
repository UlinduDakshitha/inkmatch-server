package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.VerificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<VerificationRequest, Long> {}

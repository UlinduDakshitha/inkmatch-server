package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {
}

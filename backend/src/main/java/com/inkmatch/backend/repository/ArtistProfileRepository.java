package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.ArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {
    Optional<ArtistProfile> findByUserId(Long userId);
}
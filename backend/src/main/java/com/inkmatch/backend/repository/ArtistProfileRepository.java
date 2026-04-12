package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.ArtistProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistProfileRepository extends JpaRepository<ArtistProfile, Long> {
}
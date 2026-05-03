package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<AvailabilitySlot, Long> {

    List<AvailabilitySlot> findByArtistIdAndDate(Long artistId, LocalDate date);
}

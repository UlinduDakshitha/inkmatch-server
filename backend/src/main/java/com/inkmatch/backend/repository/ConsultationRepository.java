package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    // Optional (useful for dashboard)
    List<Consultation> findByCustomer_Id(Long customerId);

    List<Consultation> findByArtist_Id(Long artistId);
}
package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Consultation;
import com.inkmatch.backend.enums.ConsultationStatus;
import com.inkmatch.backend.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultations")
@RequiredArgsConstructor
public class ConsultationController {

    private final ConsultationService consultationService;

    // Create consultation
    @PostMapping("/{customerId}/{artistId}")
    public Consultation create(@PathVariable Long customerId,
                               @PathVariable Long artistId,
                               @RequestBody Consultation consultation) {
        return consultationService.create(customerId, artistId, consultation);
    }

    // Update status
    @PutMapping("/{id}")
    public Consultation updateStatus(@PathVariable Long id,
                                     @RequestParam ConsultationStatus status) {
        return consultationService.updateStatus(id, status);
    }

    @GetMapping
    public List<Consultation> getAll() {
        return consultationService.getAll();
    }
}
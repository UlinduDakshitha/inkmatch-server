package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.Consultation;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.enums.ConsultationStatus;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.ConsultationRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final UserRepository userRepository;
    private final ArtistProfileRepository artistRepository;
    private final NotificationService notificationService; // 🔥 add karapu eka

    // 🟢 Customer sends consultation
    public Consultation create(Long customerId, Long artistId, Consultation consultation) {

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        ArtistProfile artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        consultation.setCustomer(customer);
        consultation.setArtist(artist);
        consultation.setStatus(ConsultationStatus.PENDING);
        consultation.setCreatedAt(LocalDateTime.now());

        return consultationRepository.save(consultation);
    }

    // 🟡 Artist approves / rejects consultation
    public Consultation updateStatus(Long id, ConsultationStatus status) {

        Consultation consultation = consultationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        consultation.setStatus(status);

        // 🔥 Notification send karanawa ONLY if accepted
        if (status == ConsultationStatus.APPROVED) {
            notificationService.sendNotification(
                    consultation.getCustomer().getId(),
                    "Your consultation has been accepted!"
            );
        }

        return consultationRepository.save(consultation);
    }

    // 🔵 Get all consultations
    public List<Consultation> getAll() {
        return consultationRepository.findAll();
    }
}
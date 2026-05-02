package com.inkmatch.backend.service;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.enums.Role;
import com.inkmatch.backend.enums.VerificationStatus;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.BookingRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final BookingRepository bookingRepository;
    private final ArtistProfileRepository artistRepository;

    public void verifyArtist(Long userId){

        ArtistProfile artist = artistRepository.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist profile not found"));

        artist.setVerified(true);

        artistRepository.save(artist);
    }
    public void approveArtist(Long userId){

        ArtistProfile artist = artistRepository.findByUserId(userId)
                .orElseThrow();

        artist.setVerified(true);

        artistRepository.save(artist);
    }

    public void updateVerification(Long artistId, String status) {

        ArtistProfile artist = artistRepository.findById(artistId).orElseThrow();

        if (status.equalsIgnoreCase("APPROVED")) {
            artist.setVerificationStatus(VerificationStatus.APPROVED);
        } else {
            artist.setVerificationStatus(VerificationStatus.REJECTED);
        }

        artistRepository.save(artist);
    }

    public Map<String, Object> getStats() {

        Map<String, Object> stats = new HashMap<>();

        stats.put("users", userRepository.count());
        stats.put("artists", userRepository.countByRole(Role.ARTIST));
        stats.put("bookings", bookingRepository.count());
        stats.put("pendingArtists",
                artistRepository.countByVerificationStatus(VerificationStatus.PENDING));

        return stats;
    }
}

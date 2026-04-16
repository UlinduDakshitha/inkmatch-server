package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final ArtistProfileRepository artistRepository;

    public void verifyArtist(Long userId){

        ArtistProfile artist = artistRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Artist profile not found"));

        artist.setVerified(true);

        artistRepository.save(artist);
    }
}

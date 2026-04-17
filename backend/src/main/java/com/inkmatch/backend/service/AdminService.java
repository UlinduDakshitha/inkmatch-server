package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

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
}

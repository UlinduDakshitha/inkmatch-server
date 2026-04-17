package com.inkmatch.backend.service;

import com.inkmatch.backend.dto.request.ArtistProfileDTO;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistService {

    private final ArtistProfileRepository artistRepository;
    private final UserRepository userRepository;

    public ArtistProfile createProfile(Long userId, ArtistProfileDTO dto) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        ArtistProfile profile = ArtistProfile.builder()
                .user(user)
                .bio(dto.getBio())
                .specialties(dto.getSpecialties())
                .experienceYears(dto.getExperienceYears())
                .location(dto.getLocation())
                .verified(false)
                .rating(0.0)
                .build();

        return artistRepository.save(profile);
    }

    public List<ArtistProfile> getAllArtists() {
        return artistRepository.findAll();
    }
}
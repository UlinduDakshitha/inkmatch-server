package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.Studio;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.StudioRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudioService {

    private final StudioRepository studioRepository;
    private final UserRepository userRepository;

    public Studio createStudio(Long ownerId, Studio studio) {

        User owner = userRepository.findById(ownerId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        studio.setOwner(owner);
        studio.setVerified(false);

        return studioRepository.save(studio);
    }
}
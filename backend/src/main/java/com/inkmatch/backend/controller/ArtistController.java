package com.inkmatch.backend.controller;

import com.inkmatch.backend.dto.request.ArtistProfileDTO;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/artists")
@RequiredArgsConstructor
public class ArtistController {

    private final ArtistService artistService;

    @PostMapping("/{userId}")
    public ArtistProfile create(@PathVariable Long userId,
                                @RequestBody ArtistProfileDTO dto) {
        return artistService.createProfile(userId, dto);
    }

    @GetMapping
    public List<ArtistProfile> getAll() {
        return artistService.getAllArtists();
    }
}
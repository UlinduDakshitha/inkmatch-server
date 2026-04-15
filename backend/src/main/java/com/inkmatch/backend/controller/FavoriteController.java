package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Favorite;
import com.inkmatch.backend.repository.FavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteRepository favoriteRepository;

    @PostMapping
    public ResponseEntity<Favorite> add(@RequestBody Favorite favorite) {
        return ResponseEntity.ok(favoriteRepository.save(favorite));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Favorite>> get(@PathVariable Long id) {
        return ResponseEntity.ok(favoriteRepository.findByCustomerId(id));
    }
}

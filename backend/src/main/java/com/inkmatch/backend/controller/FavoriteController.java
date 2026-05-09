package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Favorite;
import com.inkmatch.backend.service.FavoriteService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final FavoriteService favoriteService;

    @Data
    public static class FavoriteRequest {
        private Long userId;
        private Long artistId;
    }

    @PostMapping
    public ResponseEntity<Void> add(@RequestBody FavoriteRequest request) {
        favoriteService.toggleFavorite(request.getUserId(), request.getArtistId());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<List<Favorite>> get(@PathVariable Long id) {
        return ResponseEntity.ok(favoriteService.getFavoritesByCustomer(id));
    }
}

package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Review;
import com.inkmatch.backend.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> create(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.create(review));
    }

    @GetMapping("/artist/{id}")
    public ResponseEntity<List<Review>> getByArtist(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getByArtist(id));
    }
}

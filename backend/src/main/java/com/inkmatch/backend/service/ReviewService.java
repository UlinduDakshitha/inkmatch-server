package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.Review;
import com.inkmatch.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getByArtist(Long artistId) {
        return reviewRepository.findByArtistId(artistId);
    }
}

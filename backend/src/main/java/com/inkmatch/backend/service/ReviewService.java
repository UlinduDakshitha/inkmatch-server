package com.inkmatch.backend.service;
import com.inkmatch.backend.entity.Review;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.repository.BookingRepository;
import com.inkmatch.backend.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> getByArtist(Long artistId) {
        return reviewRepository.findByArtistId(artistId);
    }
    public Review addReview(Review review){

        boolean hasCompletedBooking =
                bookingRepository.existsByCustomerIdAndArtistIdAndStatus(
                        review.getCustomer().getId(),
                        review.getArtist().getId(),
                        BookingStatus.COMPLETED
                );

        if(!hasCompletedBooking){
            throw new RuntimeException("You must complete a booking to review");
        }

        return reviewRepository.save(review);
    }
}

package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomerId(Long customerId);

    List<Booking> findByArtistId(Long artistId);

    boolean existsByCustomerIdAndArtistIdAndStatus(Long customerId, Long artistId, BookingStatus status);
    long count();

    List<Booking> findByStudioId(Long studioId);

     

}

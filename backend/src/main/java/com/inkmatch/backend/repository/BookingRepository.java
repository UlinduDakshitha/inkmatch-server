package com.inkmatch.backend.repository;

import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.enums.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findByCustomer_Id(Long customerId);

    List<Booking> findByArtist_Id(Long artistId);

    boolean existsByCustomer_IdAndArtist_IdAndStatus(Long customerId, Long artistId, BookingStatus status);
    long count();

    List<Booking> findByStudio_Id(Long studioId);

     

}

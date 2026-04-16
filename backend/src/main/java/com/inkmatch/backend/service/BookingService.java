package com.inkmatch.backend.service;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.entity.Consultation;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.enums.ConsultationStatus;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.BookingRepository;
import com.inkmatch.backend.repository.ConsultationRepository;
import com.inkmatch.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ArtistProfileRepository artistRepository;
    private final ConsultationRepository consultationRepository;

    // Create booking
    public Booking create(Long customerId, Long artistId, Booking booking) {

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        ArtistProfile artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        booking.setCustomer(customer);
        booking.setArtist(artist);
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }




    public Booking createBooking(Long consultationId){

        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new RuntimeException("Consultation not found"));

        if(consultation.getStatus() != ConsultationStatus.APPROVED){
            throw new RuntimeException("Consultation not accepted yet");
        }

        Booking booking = new Booking();
        booking.setCustomer(consultation.getCustomer());
        booking.setArtist(consultation.getArtist());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }


    public Booking updateStatus(Long bookingId, BookingStatus status){

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(status);

        return bookingRepository.save(booking);
    }

}
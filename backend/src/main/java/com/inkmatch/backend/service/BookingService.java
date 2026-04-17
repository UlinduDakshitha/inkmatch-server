package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.entity.Consultation;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.enums.ConsultationStatus;
import com.inkmatch.backend.exception.ResourceNotFoundException;
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
    private final NotificationService notificationService; // 🔥 ADD THIS

    // 🟢 Create booking manually
    public Booking create(Long customerId, Long artistId, Booking booking) {

        User customer = userRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        ArtistProfile artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        booking.setCustomer(customer);
        booking.setArtist(artist);
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());

        return bookingRepository.save(booking);
    }

    // 🔥 Create booking from consultation
    public Booking createBooking(Long consultationId){

        Consultation consultation = consultationRepository.findById(consultationId)
                .orElseThrow(() -> new ResourceNotFoundException("Consultation not found"));

        if(consultation.getStatus() != ConsultationStatus.APPROVED){
            throw new ResourceNotFoundException("Consultation not accepted yet");
        }

        Booking booking = new Booking();
        booking.setCustomer(consultation.getCustomer());
        booking.setArtist(consultation.getArtist());
        booking.setBookingDate(LocalDateTime.now());
        booking.setStatus(BookingStatus.PENDING);
        booking.setCreatedAt(LocalDateTime.now());

        Booking savedBooking = bookingRepository.save(booking);

        // 🔔 Notification send
        notificationService.sendNotification(
                consultation.getCustomer().getId(),
                "Your booking has been created!"
        );

        return savedBooking;
    }

    // 🔄 Update booking status
    public Booking updateStatus(Long bookingId, BookingStatus status){

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(status);

        Booking updatedBooking = bookingRepository.save(booking);

        // 🔔 Notification when confirmed
        if(status == BookingStatus.CONFIRMED){
            notificationService.sendNotification(
                    booking.getCustomer().getId(),
                    "Your booking has been confirmed!"
            );
        }

        // 🔔 Notification when completed
        if(status == BookingStatus.COMPLETED){
            notificationService.sendNotification(
                    booking.getCustomer().getId(),
                    "Your booking has been completed!"
            );
        }

        return updatedBooking;
    }

    // 🔵 Get all bookings
    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }
}
package com.inkmatch.backend.service;

import com.inkmatch.backend.dto.request.BookingRequest;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.AvailabilitySlot;
import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.entity.Consultation;
import com.inkmatch.backend.entity.User;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.enums.ConsultationStatus;
import com.inkmatch.backend.exception.BadRequestException;
import com.inkmatch.backend.exception.ResourceNotFoundException;
import com.inkmatch.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ArtistProfileRepository artistRepository;
    private final ConsultationRepository consultationRepository;
    private final NotificationService notificationService;
    private final EmailService emailService;
    private final AvailabilityRepository availabilityRepository;

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

        reserveAvailabilitySlot(artistId, booking.getDate(), booking.getTime());

        return bookingRepository.save(booking);
    }

    public Booking createBooking(BookingRequest request) {

        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        ArtistProfile artist = artistRepository.findById(request.getArtistId())
                .orElseThrow(() -> new ResourceNotFoundException("Artist not found"));

        reserveAvailabilitySlot(request.getArtistId(), request.getDate(), request.getTime());

        Booking booking = new Booking();
        booking.setCustomer(customer);
        booking.setArtist(artist);
        booking.setDate(request.getDate());
        booking.setTime(request.getTime());
        booking.setStudioLocation(request.getStudioLocation());
        booking.setNotes(request.getNotes());
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


        return updatedBooking;
    }

    // 🔵 Get all bookings
    public List<Booking> getAll(){
        return bookingRepository.findAll();
    }

    public void confirmBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.CONFIRMED);
        Booking confirmedBooking = bookingRepository.save(booking);

        emailService.sendBookingConfirmationEmail(
                confirmedBooking.getCustomer() != null ? confirmedBooking.getCustomer().getEmail() : null,
                getArtistDisplayName(confirmedBooking.getArtist()),
                getBookingDateText(confirmedBooking),
                getBookingTimeText(confirmedBooking),
                confirmedBooking.getStudioLocation() != null ? confirmedBooking.getStudioLocation() : "N/A"
        );
    }

    public void rejectBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setStatus(BookingStatus.REJECTED);
        bookingRepository.save(booking);
    }

    private String getArtistDisplayName(ArtistProfile artist) {
        if (artist == null) {
            return "Artist";
        }

        if (artist.getName() != null && !artist.getName().isBlank()) {
            return artist.getName();
        }

        if (artist.getUser() != null && artist.getUser().getFullName() != null && !artist.getUser().getFullName().isBlank()) {
            return artist.getUser().getFullName();
        }

        return "Artist";
    }

    private String getBookingDateText(Booking booking) {
        if (booking.getDate() != null) {
            return booking.getDate().toString();
        }

        if (booking.getBookingDate() != null) {
            return booking.getBookingDate().toLocalDate().toString();
        }

        return "N/A";
    }

    private String getBookingTimeText(Booking booking) {
        if (booking.getTime() != null) {
            return booking.getTime().toString();
        }

        if (booking.getBookingTime() != null && !booking.getBookingTime().isBlank()) {
            return booking.getBookingTime();
        }

        return "N/A";
    }

    private void reserveAvailabilitySlot(Long artistId, LocalDate date, LocalTime time) {
        if (date == null || time == null) {
            throw new BadRequestException("Booking date and time are required");
        }

        AvailabilitySlot slot = availabilityRepository
                .findByArtistIdAndDate(artistId, date)
                .stream()
                .filter(s -> time.equals(s.getTime()) && !s.isBooked())
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Slot not available"));

        slot.setBooked(true);
        availabilityRepository.save(slot);
    }
    public List<Booking> getArtistBookings(Long artistId) {
        return bookingRepository.findByArtistId(artistId);
    }

    public List<Booking> getCustomerBookings(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }

    public List<Booking> getStudioBookings(Long studioId) {
        return bookingRepository.findByStudioId(studioId);
    }

    public Booking create(Booking booking) {
        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }
}
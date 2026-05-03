package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.repository.BookingRepository;
import com.inkmatch.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final BookingRepository bookingRepository;

    @PostMapping("/{customerId}/{artistId}")
    public Booking create(@PathVariable Long customerId,
                          @PathVariable Long artistId,
                          @RequestBody Booking booking) {
        return bookingService.create(customerId, artistId, booking);
    }

    @PutMapping("/{id}/status")
    public Booking updateStatus(@PathVariable Long id, @RequestParam BookingStatus status){
        return bookingService.updateStatus(id, status);
    }

    @PostMapping("/create/{consultationId}")
    public Booking create(@PathVariable Long consultationId){
        return bookingService.createBooking(consultationId);
    }

    @GetMapping("/artist/{artistId}")
    public List<Booking> getArtistBookings(@PathVariable Long artistId) {
        return bookingRepository.findByArtistId(artistId);
    }
    @PutMapping("/confirm/{id}")
    public void confirm(@PathVariable Long id) {
        bookingService.confirmBooking(id);
    }

    @PutMapping("/reject/{id}")
    public void reject(@PathVariable Long id) {
        bookingService.rejectBooking(id);
    }
    @GetMapping("/studio/{studioId}")
    public List<Booking> getStudioBookings(@PathVariable Long studioId) {
        return bookingRepository.findByStudioId(studioId);
    }
}
package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Booking;
import com.inkmatch.backend.enums.BookingStatus;
import com.inkmatch.backend.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping("/{customerId}/{artistId}")
    public Booking create(@PathVariable Long customerId,
                          @PathVariable Long artistId,
                          @RequestBody Booking booking) {
        return bookingService.create(customerId, artistId, booking);
    }

    @PutMapping("/{id}")
    public Booking updateStatus(@PathVariable Long id,
                                @RequestParam BookingStatus status) {
        return bookingService.updateStatus(id, status);
    }

    @GetMapping
    public List<Booking> getAll() {
        return bookingService.getAll();
    }
}
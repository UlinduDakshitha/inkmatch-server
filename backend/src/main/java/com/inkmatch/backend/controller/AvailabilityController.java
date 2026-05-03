package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.AvailabilitySlot;
import com.inkmatch.backend.repository.AvailabilityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/availability")
@RequiredArgsConstructor
public class AvailabilityController {

    private final AvailabilityRepository availabilityRepository;

    @PostMapping
    public void addSlot(@RequestBody AvailabilitySlot slot) {
        slot.setBooked(false);
        availabilityRepository.save(slot);
    }

    @GetMapping("/{artistId}/{date}")
    public List<AvailabilitySlot> getSlots(
            @PathVariable Long artistId,
            @PathVariable String date
    ) {
        return availabilityRepository.findByArtistIdAndDate(
                artistId,
                LocalDate.parse(date)
        );
    }
}
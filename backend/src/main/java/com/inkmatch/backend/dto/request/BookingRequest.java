package com.inkmatch.backend.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookingRequest {
    private Long customerId;
    private Long artistId;
    private LocalDate date;
    private LocalTime time;
    private String studioLocation;
    private String notes;
}

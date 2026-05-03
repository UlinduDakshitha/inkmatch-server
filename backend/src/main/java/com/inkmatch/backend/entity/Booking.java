package com.inkmatch.backend.entity;

import com.inkmatch.backend.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private ArtistProfile artist;

    private LocalDateTime bookingDate;
    private String bookingTime;

    private String notes;

    private LocalDate date;
    private LocalTime time;
    private String studioLocation;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    private Studio studio;
}
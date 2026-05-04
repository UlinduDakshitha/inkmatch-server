package com.inkmatch.backend.entity;

import com.inkmatch.backend.enums.ConsultationStatus;
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
public class Consultation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User customer;

    @ManyToOne
    private ArtistProfile artist;

    private String description;
    private String tattooStyle;
    private String placement;
    private String budget;

    private String referenceImageUrl;

    @Transient
    private Long artistId;
    @Transient
    private Long customerId;

    private LocalDate date;
    private LocalTime time;



    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;

    private LocalDateTime createdAt;
}
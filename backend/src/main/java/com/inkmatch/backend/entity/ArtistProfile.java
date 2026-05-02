package com.inkmatch.backend.entity;

import com.inkmatch.backend.enums.VerificationStatus;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String bio;
    private String specialties;
    private int experienceYears;
    private String location;
    private boolean verified;
    private double rating;


    private String name;
    private String city;
    private String style;
    private String email;

    @Enumerated(EnumType.STRING)
    private VerificationStatus verificationStatus;

}
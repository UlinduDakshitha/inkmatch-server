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
public class Studio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String name;
    private String description;
    private String location;
    private String contactNumber;
    private VerificationStatus verificationStatus;
    private boolean suspended;

    @ManyToOne
    private User owner;

    private boolean verified;
}
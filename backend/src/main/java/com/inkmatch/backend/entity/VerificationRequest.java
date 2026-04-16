package com.inkmatch.backend.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
@Entity

public class VerificationRequest {

        @Id
        @GeneratedValue
        private Long id;

        private Long userId;
        private String status;

}

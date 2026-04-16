package com.inkmatch.backend.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
public class PortfolioImage {

    @Id
    @GeneratedValue
    private Long id;

    private Long portfolioId;
    private String imageUrl;
}
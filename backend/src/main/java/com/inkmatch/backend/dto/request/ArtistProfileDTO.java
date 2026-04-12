package com.inkmatch.backend.dto.request;

import lombok.Data;

@Data
public class ArtistProfileDTO {
    private String bio;
    private String specialties;
    private int experienceYears;
    private String location;
}

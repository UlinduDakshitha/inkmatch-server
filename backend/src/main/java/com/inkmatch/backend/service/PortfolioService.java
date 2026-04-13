package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.Portfolio;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final ArtistProfileRepository artistRepository;

    public Portfolio createPortfolio(Long artistId, Portfolio portfolio) {

        ArtistProfile artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        portfolio.setArtist(artist);
        portfolio.setCreatedAt(LocalDateTime.now());

        return portfolioRepository.save(portfolio);
    }
}
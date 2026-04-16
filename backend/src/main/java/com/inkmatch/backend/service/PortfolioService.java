package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.entity.Portfolio;
import com.inkmatch.backend.entity.PortfolioImage;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import com.inkmatch.backend.repository.PortfolioImageRepository;
import com.inkmatch.backend.repository.PortfolioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class PortfolioService {

    private final PortfolioRepository portfolioRepository;
    private final ArtistProfileRepository artistRepository;
    private final PortfolioImageRepository imageRepository;
    private final ImageService imageService;

    public PortfolioService(
            PortfolioRepository portfolioRepository,
            ArtistProfileRepository artistRepository,
            PortfolioImageRepository imageRepository,
            ImageService imageService
    ) {
        this.portfolioRepository = portfolioRepository;
        this.artistRepository = artistRepository;
        this.imageRepository = imageRepository;
        this.imageService = imageService;
    }

    public Portfolio createPortfolio(Long artistId, Portfolio portfolio) {

        ArtistProfile artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        portfolio.setArtist(artist);
        portfolio.setCreatedAt(LocalDateTime.now());

        return portfolioRepository.save(portfolio);
    }

    public String uploadAndSave(Long portfolioId, MultipartFile file) throws IOException {

        if (!portfolioRepository.existsById(portfolioId)) {
            throw new RuntimeException("Portfolio not found");
        }

        String url = imageService.upload(file);

        PortfolioImage img = new PortfolioImage();
        img.setPortfolioId(portfolioId);
        img.setImageUrl(url);

        imageRepository.save(img);

        return url;
    }
}
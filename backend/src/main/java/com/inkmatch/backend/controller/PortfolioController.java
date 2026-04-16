package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Portfolio;
import com.inkmatch.backend.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/portfolios")
@RequiredArgsConstructor
public class PortfolioController {

    private final PortfolioService portfolioService;

    @PostMapping("/{artistId}")
    public Portfolio create(@PathVariable Long artistId,
                            @RequestBody Portfolio portfolio) {
        return portfolioService.createPortfolio(artistId, portfolio);
    }
    @PostMapping("/{portfolioId}/upload")
    public String uploadImage(
            @PathVariable Long portfolioId,
            @RequestParam MultipartFile file
    ) throws IOException {

        return portfolioService.uploadAndSave(portfolioId, file);
    }
}
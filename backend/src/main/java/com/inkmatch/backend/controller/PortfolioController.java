package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Portfolio;
import com.inkmatch.backend.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
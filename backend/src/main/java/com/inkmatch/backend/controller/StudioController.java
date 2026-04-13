package com.inkmatch.backend.controller;

import com.inkmatch.backend.entity.Studio;
import com.inkmatch.backend.service.StudioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/studios")
@RequiredArgsConstructor
public class StudioController {

    private final StudioService studioService;

    @PostMapping("/{ownerId}")
    public Studio create(@PathVariable Long ownerId,
                         @RequestBody Studio studio) {
        return studioService.createStudio(ownerId, studio);
    }
}
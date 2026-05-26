package com.inkmatch.backend.controller;
import com.inkmatch.backend.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PutMapping("/verify/{userId}")
    public void verify(@PathVariable Long userId){
        adminService.verifyArtist(userId);
    }

    @GetMapping("/stats")
    public Map<String, Object> stats() {
        return adminService.getStats();
    }

    @PutMapping("/verification/{id}")
    public void verify(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        adminService.updateVerification(id, status);
    }
}

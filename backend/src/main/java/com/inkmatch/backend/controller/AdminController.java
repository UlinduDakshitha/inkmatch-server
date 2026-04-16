package com.inkmatch.backend.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class AdminController {

    @PutMapping("/verify/{userId}")
    public void verify(@PathVariable Long userId){
        adminService.approveArtist(userId);
    }
}

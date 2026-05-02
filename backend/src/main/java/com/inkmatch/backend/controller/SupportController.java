package com.inkmatch.backend.controller;

import com.inkmatch.backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/support")
@CrossOrigin(origins = "*")
public class SupportController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public Map<String, String> sendSupport(@RequestBody Map<String, String> request) {

        String email = request.get("email");

        emailService.sendSupportEmail(email);

        return Map.of("message", "Support request sent successfully ✅");
    }
}

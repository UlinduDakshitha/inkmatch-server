package com.inkmatch.backend.controller;

import com.inkmatch.backend.dto.request.ChatRequest;
import com.inkmatch.backend.dto.response.ChatResponse;
import com.inkmatch.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ChatResponse chat(@RequestBody ChatRequest request) {
        String reply = chatService.getReply(request.getMessage());
        return new ChatResponse(reply);
    }
}
package com.inkmatch.backend.service;

import com.inkmatch.backend.config.GeminiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GeminiConfig config;

    public String getReply(String userMessage) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                + config.getModel()
                + ":generateContent?key=" + config.getApiKey();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // 🔥 SYSTEM PROMPT (VERY IMPORTANT)
        String systemPrompt = """
                You are an AI assistant for InkMatch tattoo marketplace.
                Help users to:
                - find tattoo artists
                - understand pricing
                - guide booking process
                - suggest tattoo styles
                Keep answers short and helpful.
                """;

        Map<String, Object> body = new HashMap<>();

        Map<String, Object> part = Map.of(
                "text", systemPrompt + "\nUser: " + userMessage
        );

        Map<String, Object> content = Map.of(
                "parts", List.of(part)
        );

        body.put("contents", List.of(content));

        HttpEntity<Map<String, Object>> request =
                new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response =
                    restTemplate.postForEntity(url, request, Map.class);

            List<Map<String, Object>> candidates =
                    (List<Map<String, Object>>) response.getBody().get("candidates");

            Map<String, Object> first = candidates.get(0);
            Map<String, Object> contentMap =
                    (Map<String, Object>) first.get("content");

            List<Map<String, Object>> parts =
                    (List<Map<String, Object>>) contentMap.get("parts");

            return parts.get(0).get("text").toString();

        } catch (Exception e) {
            return "AI response error 😢";
        }
    }
}
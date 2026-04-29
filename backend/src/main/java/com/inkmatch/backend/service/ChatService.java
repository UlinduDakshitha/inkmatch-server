package com.inkmatch.backend.service;

import com.inkmatch.backend.config.GeminiConfig;
import com.inkmatch.backend.entity.ArtistProfile;
import com.inkmatch.backend.repository.ArtistProfileRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ChatService {

    private static final String AI_ERROR_MESSAGE = "AI response error 😢";

    private final RestTemplate restTemplate;

    private final GeminiConfig config;

    private final ArtistProfileRepository artistProfileRepository;

    public ChatService(RestTemplate restTemplate, GeminiConfig config, ArtistProfileRepository artistProfileRepository) {
        this.restTemplate = restTemplate;
        this.config = config;
        this.artistProfileRepository = artistProfileRepository;
    }

    public String getReply(String userMessage) {
        String safeMessage = userMessage == null ? "" : userMessage.trim();
        String prompt = buildPromptWithArtists(safeMessage);
        return callGemini(prompt);
    }

    private String buildPromptWithArtists(String userMessage) {
        String systemPrompt = """
                You are an AI assistant for InkMatch tattoo marketplace.
                Help users to:
                - find tattoo artists
                - understand pricing
                - guide booking process
                - suggest tattoo styles
                Keep answers short, friendly, and helpful.
                If matching artists are provided, recommend them briefly.
                """;

        String artistContext = buildArtistContext(userMessage);

        if (artistContext.isBlank()) {
            return systemPrompt + "\nUser: " + userMessage;
        }

        return systemPrompt + "\n\n" + artistContext + "\nUser: " + userMessage;
    }

    private String buildArtistContext(String userMessage) {
        List<ArtistProfile> matchedArtists = findMatchingArtists(userMessage);

        if (matchedArtists.isEmpty()) {
            return "";
        }

        StringBuilder context = new StringBuilder("Available artists:\n");

        for (ArtistProfile artist : matchedArtists) {
            context.append("- ")
                    .append(defaultText(artist.getName(), "Unknown artist"))
                    .append(" (")
                    .append(defaultText(artist.getCity(), defaultText(artist.getLocation(), "Unknown location")));

            if (artist.getStyle() != null && !artist.getStyle().isBlank()) {
                context.append(", style: ").append(artist.getStyle());
            }

            context.append(", rating: ")
                    .append(artist.getRating())
                    .append(")\n");
        }

        return context.toString();
    }

    private List<ArtistProfile> findMatchingArtists(String userMessage) {
        if (userMessage == null || userMessage.isBlank()) {
            return List.of();
        }

        String message = userMessage.toLowerCase(Locale.ROOT);

        return artistProfileRepository.findAll().stream()
                .filter(artist -> matchesArtist(message, artist))
                .sorted(Comparator.comparingDouble(ArtistProfile::getRating).reversed())
                .limit(5)
                .toList();
    }

    private boolean matchesArtist(String message, ArtistProfile artist) {
        return containsIgnoreCase(message, artist.getName())
                || containsIgnoreCase(message, artist.getCity())
                || containsIgnoreCase(message, artist.getLocation())
                || containsIgnoreCase(message, artist.getStyle())
                || containsIgnoreCase(message, artist.getSpecialties());
    }

    private boolean containsIgnoreCase(String message, String value) {
        return value != null && !value.isBlank() && message.contains(value.toLowerCase(Locale.ROOT));
    }

    private String defaultText(String value, String fallback) {
        return value == null || value.isBlank() ? fallback : value;
    }

    private String callGemini(String prompt) {

        String url = "https://generativelanguage.googleapis.com/v1beta/models/"
                + config.getModel()
                + ":generateContent?key=" + config.getApiKey();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = new HashMap<>();

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));

        body.put("contents", List.of(content));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map<String, Object>> response = restTemplate.exchange(
                    url,
                    HttpMethod.POST,
                    request,
                    new ParameterizedTypeReference<>() {
                    }
            );

            Map<String, Object> responseBody = response.getBody();
            if (responseBody == null || !responseBody.containsKey("candidates")) {
                return AI_ERROR_MESSAGE;
            }

            Object candidatesObj = responseBody.get("candidates");
            if (!(candidatesObj instanceof List<?> candidates) || candidates.isEmpty()) {
                return AI_ERROR_MESSAGE;
            }

            Object firstObj = candidates.get(0);
            if (!(firstObj instanceof Map<?, ?> first)) {
                return AI_ERROR_MESSAGE;
            }

            Object contentObj = first.get("content");
            if (!(contentObj instanceof Map<?, ?> contentMap)) {
                return AI_ERROR_MESSAGE;
            }

            Object partsObj = contentMap.get("parts");
            if (!(partsObj instanceof List<?> parts) || parts.isEmpty()) {
                return AI_ERROR_MESSAGE;
            }

            Object firstPartObj = parts.get(0);
            if (!(firstPartObj instanceof Map<?, ?> firstPart)) {
                return AI_ERROR_MESSAGE;
            }

            Object text = firstPart.get("text");
            if (text == null) {
                return AI_ERROR_MESSAGE;
            }

            return text.toString();


        } catch (Exception e) {
            return AI_ERROR_MESSAGE;
        }
    }
}
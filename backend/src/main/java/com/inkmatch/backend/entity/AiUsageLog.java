package com.inkmatch.backend.entity;

import java.time.LocalDateTime;

public class AiUsageLog {
    private Long id;
    private Long userId;
    private String modelName;
    private String promptType;
    private Integer requestCount;
    private LocalDateTime createdAt;
}

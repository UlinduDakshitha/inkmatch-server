package com.inkmatch.backend.entity;

import java.time.LocalDateTime;

public class Report {
    private Long id;
    private Long reporterId;
    private String targetType;   // USER / ARTIST / STUDIO / BOOKING
    private Long targetId;
    private String reason;
    private String status;       // OPEN / RESOLVED / DISMISSED
    private LocalDateTime createdAt;
}

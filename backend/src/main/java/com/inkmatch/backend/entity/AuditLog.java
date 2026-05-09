package com.inkmatch.backend.entity;

import java.time.LocalDateTime;

public class AuditLog {
    private Long id;
    private Long adminId;
    private String actionType;   // APPROVE, REJECT, SUSPEND, DELETE
    private String targetType;
    private Long targetId;
    private String notes;
    private LocalDateTime createdAt;
}

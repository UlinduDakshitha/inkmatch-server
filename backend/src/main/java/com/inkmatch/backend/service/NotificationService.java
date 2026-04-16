package com.inkmatch.backend.service;

import com.inkmatch.backend.entity.Notification;
import com.inkmatch.backend.repository.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Long userId, String message) {
        Notification n = new Notification();
        n.setUserId(userId);
        n.setMessage(message);
        n.setIsRead(false);
        notificationRepository.save(n);
    }
}

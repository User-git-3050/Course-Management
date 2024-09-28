package az.msnotification.service;

import az.msnotification.dao.NotificationRequest;
import az.msnotification.dao.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationService {
    ResponseEntity<NotificationResponse> sendCustomNotification(NotificationRequest notificationRequest);
    ResponseEntity<List<NotificationResponse>> getAllNotifications();
    ResponseEntity<NotificationResponse> getNotificationById(Long id);
    void triggerReminders();
}

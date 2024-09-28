package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.NotificationRequest;
import az.mscoursedictionary.dao.NotificationResponse;
import az.mscoursedictionary.entity.NotificationEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NotificationDictionaryService {
    NotificationResponse sendCustomNotification(NotificationRequest notificationRequest);
    List<NotificationResponse> getAllNotifications();
    NotificationResponse getNotificationById(Long notificationId);
}

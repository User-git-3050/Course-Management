package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.NotificationRequest;
import az.mscoursedictionary.dao.NotificationResponse;
import az.mscoursedictionary.entity.EnrollmentEntity;
import az.mscoursedictionary.entity.NotificationEntity;
import az.mscoursedictionary.enums.NotificationStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public enum NotificationMapper {
    NOTIFICATION_MAPPER;

    public NotificationEntity mapToEntity(NotificationRequest notificationRequest, List<EnrollmentEntity> enrollments) {
        return NotificationEntity.builder()
                .title(notificationRequest.getTitle())
                .enrollments(enrollments)
                .notificationDate(LocalDateTime.now())
                .notificationStatus(NotificationStatus.valueOf(notificationRequest.getNotificationStatus().toUpperCase()))
                .message(notificationRequest.getMessage())
                .build();
    }
    public NotificationResponse mapToResponse(NotificationEntity notificationEntity){
        return NotificationResponse.builder()
                .id(notificationEntity.getId())
                .title(notificationEntity.getTitle())
                .message(notificationEntity.getMessage())
                .notificationStatus(String.valueOf(notificationEntity.getNotificationStatus()))
                .notificationDate(notificationEntity.getNotificationDate())
                .build();
    }
}

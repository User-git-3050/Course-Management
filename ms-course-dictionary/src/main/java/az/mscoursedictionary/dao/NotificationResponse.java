package az.mscoursedictionary.dao;

import az.mscoursedictionary.enums.NotificationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class NotificationResponse {
    private Long id;

    private String title;

    private String message;

    private LocalDateTime notificationDate;

    private String notificationStatus;


}

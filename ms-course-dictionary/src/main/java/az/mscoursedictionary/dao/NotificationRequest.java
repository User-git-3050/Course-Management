package az.mscoursedictionary.dao;

import az.mscoursedictionary.enums.NotificationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class NotificationRequest {
    private String title;

    private String message;

    private LocalDateTime notificationDate;

    private String notificationStatus;

    private List<String> receivers;
}

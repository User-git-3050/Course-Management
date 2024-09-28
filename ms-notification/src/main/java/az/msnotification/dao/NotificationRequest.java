package az.msnotification.dao;

import lombok.Builder;
import lombok.Data;

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

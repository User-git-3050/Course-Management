package az.msnotification.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class NotificationResponse {
    private Long id;

    private String title;

    private String message;

    private LocalDateTime notificationDate;


}

package az.msnotification.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EnrollmentProgress {
    private Long enrollmentId;
    private String enrollmentName;
    private String courseName;
    private Long courseId;
    private Long completedActivities;
    private LocalDateTime completionDate;
    private Long totalActivities;
    private Long progress;

}

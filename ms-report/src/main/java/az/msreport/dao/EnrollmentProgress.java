package az.msreport.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class EnrollmentProgress {
    private Long completedActivities;
    private LocalDateTime completionDate;
    private Long totalActivities;
}

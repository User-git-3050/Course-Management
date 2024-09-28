package az.msenrollment.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class EnrollmentProgress {
    private Long completedActivities;
    private LocalDate completionDate;
    private Long totalActivities;
}

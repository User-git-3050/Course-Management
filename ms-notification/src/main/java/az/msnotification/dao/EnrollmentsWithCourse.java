package az.msnotification.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentsWithCourse {
    private Long courseId;
    private String courseName;
    private Long enrollmentId;
    private String enrollmentName;

}


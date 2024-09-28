package az.msscheduler.dao.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class InstructorResponse {
    private Long id;

    private String username;

    private String email;

    private String subject;

    private Long courseAmount;

    private Long updateAmount;

    private Long numberOfStudents;

    private List<CourseResponse> courses;

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", courseAmount=" + courseAmount +
                ", updateAmount=" + updateAmount +
                ", numberOfStudents=" + numberOfStudents +
                ", courses=" + courses +
                '}';
    }
}

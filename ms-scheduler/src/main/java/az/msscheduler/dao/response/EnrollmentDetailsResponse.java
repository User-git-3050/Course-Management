package az.msscheduler.dao.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class EnrollmentDetailsResponse {

    private Long id;

    private String username;

    private String name;

    private String surname;

    private Integer age;

    private List<String> courses;

    private List<String> certificates;

    private Map<String,String> status;

    private Map<String,Long> completedActivities; //forEach course

    private Map<String,Long> progress;

    private Map<String, LocalDateTime> completionDate;

    @Override
    public String toString() {
        return "EnrollmentDetails{\n" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", courses=" + courses +
                ", certificates=" + certificates +
                ", status=" + status +
                ", completedActivities=" + completedActivities +
                ", progress=" + progress +
                ", completionDate=" + completionDate +
                '}';
    }
}
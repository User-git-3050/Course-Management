package az.msreport.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class EnrollmentDetails {
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

}

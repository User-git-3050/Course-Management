package az.msenrollment.dao;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Builder
@Getter
public class EnrollmentDetails {

    private Long id;

    private String username;

    private String surname;

    private Integer age;

    private Map<String,String> status;

    private List<String> courses;

    private List<String> certificates;

    private Map<String,Long> completedActivities; //forEach course

    private Map<String,Long> progress;

    private Map<String, LocalDate> completionDate;


}

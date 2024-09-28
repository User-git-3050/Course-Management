package az.mscoursedictionary.dao;

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

}

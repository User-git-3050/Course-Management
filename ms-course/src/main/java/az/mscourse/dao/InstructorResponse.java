package az.mscourse.dao;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class InstructorResponse {
    private Long id;

    private String username;

    private String subject;

    private Long courseAmount;

    private Long updateAmount;

    private Long numberOfStudents;

    private List<String> courses;
}

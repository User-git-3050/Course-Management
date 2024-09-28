package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CourseReport {
    private String courseName;

    private List<String> enrollments;

    private List<String> instructors;

    private List<String> completedEnrollments;

    @Override
    public String toString() {
        return "\n" +
                "courseName : '" + courseName + '\'' +
                "\nenrollments : " + enrollments +
                "\ninstructors : " + instructors +
                "\ncompletedEnrollments : " + completedEnrollments +
                "}";
    }
}

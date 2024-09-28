package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class InstructorReport {

    private String username;

    private Long courseAmount;

    private Long updateAmount;

    private Long numberOfStudents;

    private List<String> courses;

    @Override
    public String toString() {
        return "\n" +
                "username : '" + username + '\'' +
                "\ncourseAmount : " + courseAmount +
                "\nupdateAmount : " + updateAmount +
                "\nnumberOfStudents : " + numberOfStudents +
                "\ncourses : " + courses +
                '}';
    }

}

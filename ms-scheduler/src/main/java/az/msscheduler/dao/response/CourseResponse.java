package az.msscheduler.dao.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CourseResponse {
    private Long id;

    private String name;

    private String description;

    private String categoryName;

    private Double price;

    private List<String> certifications;

    private List<String> enrollments;

    private List<String> instructors;

}


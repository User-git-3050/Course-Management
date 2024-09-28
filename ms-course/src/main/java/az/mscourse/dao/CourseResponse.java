package az.mscourse.dao;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
public class CourseResponse {
    private Long id;

    private String name;

    private String description;

    private String categoryName;

    private Double price;

    private List<String> instructors;

    private List<String> enrollments;


}


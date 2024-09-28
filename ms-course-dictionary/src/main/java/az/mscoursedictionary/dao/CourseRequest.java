package az.mscoursedictionary.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CourseRequest {

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "description must be written")
    private String description;

    private String categoryName;

    private Long totalActivities;

    private Double price;

}

package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class EnrollmentResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Integer phone;
}

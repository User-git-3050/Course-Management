package az.msenrollment.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentResponse {
    private Long id;
    private String username;
    private String name;
    private String email;
    private Integer phone;

}

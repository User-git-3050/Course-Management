package az.msnotification.dao;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentResponse {
    private Long id;
    private String name;
    private String username;
    private String email;
    private Integer phone;


}

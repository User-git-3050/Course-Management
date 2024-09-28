package az.mscoursedictionary.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentRegistrationRequest {
    private String username;
    private String name;
    private String password;
    private String surname;
    private Integer phone;
    private String email;
}


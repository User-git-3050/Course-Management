package az.mscoursedictionary.dao;

import az.mscoursedictionary.enums.RoleEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {
    private Long id;

    private String username;

    private String name;

    private String password;

    private String surname;

    private String role;

    private String email;

    private Integer phone;

}

package az.mscoursedictionary.entity;

import az.mscoursedictionary.enums.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "users")
public class UserEntity {
    @Id
    @SequenceGenerator(name = "user_id", sequenceName = "user_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_id")
    private Long id;

    private String username;

    private String name;

    private String password;

    private String surname;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    private String email;

    private Integer phone;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user",fetch = FetchType.LAZY)
    private InstructorEntity instructor;

    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user",fetch = FetchType.LAZY)
    private EnrollmentEntity enrollment;
}

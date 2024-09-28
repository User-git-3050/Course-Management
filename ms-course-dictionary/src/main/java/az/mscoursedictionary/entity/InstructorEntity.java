package az.mscoursedictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "instructors")
public class InstructorEntity {
    @Id
    @SequenceGenerator(name = "instructor_id",sequenceName = "instructor_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id")
    private Long id;

    private String username;

    private String subject;

    private Long courseAmount;

    private Long updateAmount;

    private Long numberOfStudents;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "instructors", fetch = FetchType.LAZY)
    private List<CourseEntity> courses;
}

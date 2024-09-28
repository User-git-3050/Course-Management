package az.mscoursedictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "course")
public class CourseEntity { //pending
    @Id
    @SequenceGenerator(name = "course_id",sequenceName = "course_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_id")
    private Long id;

    private String name;

    private String description;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "total_activities")
    private Long totalActivities;

    private Double price;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "course_instructor",
            joinColumns = @JoinColumn (name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private List<InstructorEntity> instructors;


    @OneToMany(mappedBy = "course",fetch = FetchType.LAZY)
    private List<EnrollmentDetailsEntity> enrollmentDetails;





}

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
@Table(name = "enrollments")
public class EnrollmentEntity {
    @Id
    @SequenceGenerator(name = "enrollment_id",sequenceName = "enrollment_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "enrollment_id")
    private Long id;

    private String username;


    @OneToMany(mappedBy = "enrollment")
    private List<EnrollmentDetailsEntity> enrollmentDetails;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "enrollment_certification",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "certification_id")
    )
    private List<CertificationEntity> certifications;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "enrollment_notification",
            joinColumns = @JoinColumn(name = "enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id")
    )
    private List<NotificationEntity> notifications;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;




}

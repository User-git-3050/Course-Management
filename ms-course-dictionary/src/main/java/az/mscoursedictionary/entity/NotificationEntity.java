package az.mscoursedictionary.entity;

import az.mscoursedictionary.enums.NotificationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "notifications")
public class NotificationEntity { //user
    @Id
    @SequenceGenerator(name = "notification_id",sequenceName = "notification_id",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_id")
    private Long id;

    private String title;

    private String message;

    @Column(name = "notification_date")
    private LocalDateTime notificationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_status")
    private NotificationStatus notificationStatus;

    @ManyToMany(mappedBy = "notifications", fetch = FetchType.LAZY)
    private List<EnrollmentEntity> enrollments;






}

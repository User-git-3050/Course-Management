package az.mscoursedictionary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="certification")
public class CertificationEntity {
    @Id
    @SequenceGenerator(name = "certification_id",sequenceName = "certification_id", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "certification_id")
    private Long id;

    private String title;

    @Lob
    @Column(name = "pdf_data")
    private byte[] pdfData;

    @Column(name = "date_issued")
    private LocalDateTime dateIssued;

    @OneToOne(mappedBy = "certification",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private EnrollmentDetailsEntity enrollmentDetailsEntity;







}

package az.mscoursedictionary.dao;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
public class CertificationResponse {
    private Long id;

    private byte[] pdfData;

    private String title;

    private LocalDateTime dateIssued;

    private String enrollment;

    private String course;
}

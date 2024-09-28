package az.msscheduler.dao.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class CertificationResponse {
    private Long id;

    private byte[] pdfData;

    private String title;

    private LocalDate dateIssued;

    private String enrollment;

    private String course;
}
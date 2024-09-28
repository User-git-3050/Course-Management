package az.mscertification.service.impl;

import az.mscertification.client.CourseDictionaryClient;
import az.mscertification.dao.CertificationResponse;
import az.mscertification.dao.EnrollmentsWithCourse;
import az.mscertification.service.CertificationService;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class CertificationServiceImpl implements CertificationService {


    private final CourseDictionaryClient courseDictionaryClient;

    public CertificationServiceImpl(CourseDictionaryClient courseDictionaryClient) {
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public ResponseEntity<List<CertificationResponse>> getAllCertifications() {
        return courseDictionaryClient.getAllCertifications();
    }

    @Override
    public ResponseEntity<CertificationResponse> getCertificationById(Long certificationId) {
        return courseDictionaryClient.getCertificationById(certificationId);
    }

    @Override
    public List<CertificationResponse> generateCertification() {
        List<CertificationResponse> generatedCertifications = new ArrayList<>();
        List<EnrollmentsWithCourse> courseWithEnrollment = courseDictionaryClient.getCompletedCoursesWithEnrollments().getBody();
        if(courseWithEnrollment != null) {
            courseWithEnrollment.forEach((enrollmentsWithCourse) -> {
                byte[] pdfBytes = createPdf(enrollmentsWithCourse.getCourseName(),
                        enrollmentsWithCourse.getEnrollmentName());

                generatedCertifications.add(courseDictionaryClient.generateCertification(enrollmentsWithCourse.getCourseId(),
                        enrollmentsWithCourse.getEnrollmentId(), pdfBytes).getBody());
            });

            return generatedCertifications;
        }
        return null;
    }

    @Override
    public ResponseEntity<InputStreamResource> downloadCertificate(Long certificationId) {
        byte[] pdfData = Objects.requireNonNull(courseDictionaryClient.getCertificationById(certificationId).getBody()).getPdfData();
        ByteArrayInputStream bis = new ByteArrayInputStream(pdfData);

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Disposition", "attachment; filename=certification.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));

    }


    private byte[] createPdf(String courseName, String enrollmentName) {
        String textContext = courseName.toUpperCase()
                + "\nThis certificate was awarded to "
                + enrollmentName.toUpperCase()
                + "for completing our course"
                + "\n\nDate Issued :" + LocalDate.now();


        byte[] pdfBytes = null;


        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            document.add(new Paragraph(textContext));

            document.close();

            pdfBytes = baos.toByteArray();
            return pdfBytes;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return pdfBytes;
    }


}

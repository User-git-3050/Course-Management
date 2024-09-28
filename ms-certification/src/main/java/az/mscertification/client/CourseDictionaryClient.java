package az.mscertification.client;

import az.mscertification.dao.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {
    @GetMapping("api/certification/dictionary")
    ResponseEntity<List<CertificationResponse>> getAllCertifications();

    @GetMapping("api/certification/dictionary/{certificationId}")
    ResponseEntity<CertificationResponse> getCertificationById(@PathVariable Long certificationId);

    @PostMapping("api/certification/dictionary/generate")
    ResponseEntity<CertificationResponse> generateCertification(@RequestParam Long courseId, @RequestParam Long enrollmentId,@RequestBody byte[] pdfBytes);

    @GetMapping("api/course/dictionary/completed")
    ResponseEntity<List<EnrollmentsWithCourse>> getCompletedCoursesWithEnrollments();

    @GetMapping("api/enrollments/dictionary/{enrollmentId}/name")
    ResponseEntity<String> getEnrollmentNameWithId(@PathVariable Long enrollmentId);

    @GetMapping("{courseId}/name")
    ResponseEntity<String> getCourseNameWithId(@PathVariable Long courseId);


}

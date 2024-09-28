package az.msenrollment.client;

import az.msenrollment.dao.EnrollmentDetails;
import az.msenrollment.dao.EnrollmentProgress;
import az.msenrollment.dao.EnrollmentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {

    @PostMapping("api/enrollments/dictionary")
     void enrollStudent(@RequestParam String username, @RequestParam Long courseId);

    @GetMapping("api/enrollments/dictionary")
     ResponseEntity<List<EnrollmentResponse>> getEnrollments();

    @GetMapping("api/enrollments/dictionary/{enrollmentId}")
     ResponseEntity<EnrollmentDetails> getEnrollmentDetails(@PathVariable Long enrollmentId);

    @PutMapping("api/enrollments/dictionary/{enrollmentId}/progress")
     void updateProgress(@PathVariable Long enrollmentId,@RequestParam Long courseId ,@RequestParam Long completedActivities);

    @GetMapping("api/enrollments/dictionary/{enrollmentId}/progress")
     ResponseEntity<EnrollmentProgress> getEnrollmentProgress(@PathVariable Long enrollmentId, @RequestParam Long courseId);

    @DeleteMapping("api/enrollments/dictionary/{enrollmentId}")
     void cancelEnrollment(@PathVariable Long enrollmentId,@RequestParam Long courseId);


}

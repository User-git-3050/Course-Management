package az.msenrollment.controller;

import az.msenrollment.dao.EnrollmentDetails;
import az.msenrollment.dao.EnrollmentProgress;
import az.msenrollment.dao.EnrollmentResponse;
import az.msenrollment.service.EnrollmentService;
import jakarta.ws.rs.Path;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/enrollments/")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public String enrollStudent(@RequestHeader String username, @RequestParam Long courseId){
        return enrollmentService.enrollStudent(username, courseId);
    }

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(){
        return enrollmentService.getEnrollments();
    }

    @GetMapping("{enrollmentId}")
    public ResponseEntity<EnrollmentDetails> getEnrollmentDetails(@PathVariable Long enrollmentId){
        return enrollmentService.getEnrollmentDetails(enrollmentId);
    }

    @PutMapping("{enrollmentId}/progress")
    public void updateProgress(@PathVariable Long enrollmentId, @RequestParam Long courseId,@RequestParam Long completedActivities){
        enrollmentService.updateProgress(enrollmentId, courseId, completedActivities);
    }

    @GetMapping("{enrollmentId}/progress")
    public ResponseEntity<EnrollmentProgress> getEnrollmentProgress(@PathVariable Long enrollmentId, @RequestParam Long courseId){
        return enrollmentService.getEnrollmentProgress(enrollmentId,courseId);
    }

    @DeleteMapping("{enrollmentId}")
    public void deleteEnrollment(@PathVariable Long enrollmentId,@RequestParam Long courseId){
        enrollmentService.cancelEnrollment(enrollmentId,courseId);
    }


}

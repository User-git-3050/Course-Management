package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.EnrollmentProgress;
import az.mscoursedictionary.dao.EnrollmentDetailsResponse;
import az.mscoursedictionary.dao.EnrollmentResponse;
import az.mscoursedictionary.service.EnrollmentDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/enrollments/dictionary")
public class EnrollmentController {
    private final EnrollmentDictionaryService enrollmentDictionaryService;

    @Autowired
    public EnrollmentController(EnrollmentDictionaryService enrollmentDictionaryService) {
        this.enrollmentDictionaryService = enrollmentDictionaryService;
    }

    @PostMapping()
    public void enrollStudent(@RequestParam String username, @RequestParam Long courseId){
        enrollmentDictionaryService.enrollStudent(username, courseId);
    }

    @GetMapping()
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(){
        return ResponseEntity.ok(enrollmentDictionaryService.getAllEnrollments());
    }

    @GetMapping("{enrollmentId}")
    public ResponseEntity<EnrollmentDetailsResponse> getEnrollmentDetails(@PathVariable Long enrollmentId){
        return ResponseEntity.ok(enrollmentDictionaryService.getEnrollmentDetails(enrollmentId));
    }

    @PutMapping("{enrollmentId}/progress")
    public void updateProgress(@PathVariable Long enrollmentId,@RequestParam Long courseId ,@RequestParam Long completedActivities){
        enrollmentDictionaryService.updateProgress(enrollmentId,courseId,completedActivities);
    }

    @GetMapping("{enrollmentId}/progress")
    public ResponseEntity<EnrollmentProgress> getEnrollmentProgress(@PathVariable Long enrollmentId,@RequestParam Long courseId){
        return ResponseEntity.ok(enrollmentDictionaryService.getEnrollmentProgress(enrollmentId,courseId));
    }


    @DeleteMapping("{enrollmentId}")
    public void cancelEnrollment(@PathVariable Long enrollmentId,@RequestParam Long courseId){
        enrollmentDictionaryService.cancelEnrollment(enrollmentId,courseId);

    }

    @GetMapping("{enrollmentId}/name")
    public ResponseEntity<String> getEnrollmentNameWithId(@PathVariable Long enrollmentId){
        return ResponseEntity.ok(enrollmentDictionaryService.getEnrollmentNameWithId(enrollmentId));
    }

    @GetMapping("check/progress")
    public ResponseEntity<List<EnrollmentProgress>> checkEnrollmentProgress(){
        return ResponseEntity.ok(enrollmentDictionaryService.checkEnrollmentProgress());
    }







}

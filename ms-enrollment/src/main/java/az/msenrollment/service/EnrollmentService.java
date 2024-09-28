package az.msenrollment.service;

import az.msenrollment.dao.EnrollmentDetails;
import az.msenrollment.dao.EnrollmentProgress;
import az.msenrollment.dao.EnrollmentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentService {
    String enrollStudent(String username,Long courseId);
    ResponseEntity<List<EnrollmentResponse>> getEnrollments();
    ResponseEntity<EnrollmentDetails> getEnrollmentDetails(Long enrollmentId);
    void updateProgress(Long enrollmentId,Long courseId,Long completedActivities);
    ResponseEntity<EnrollmentProgress> getEnrollmentProgress(Long enrollmentId, Long courseId);
    void cancelEnrollment(Long enrollmentId,Long courseId);
}

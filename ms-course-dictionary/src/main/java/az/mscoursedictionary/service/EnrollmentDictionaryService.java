package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.EnrollmentProgress;
import az.mscoursedictionary.dao.EnrollmentDetailsResponse;
import az.mscoursedictionary.dao.EnrollmentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EnrollmentDictionaryService {
    void enrollStudent(String username,Long courseId);
    List<EnrollmentResponse> getAllEnrollments();
    EnrollmentDetailsResponse getEnrollmentDetails(Long enrollmentId);
    void updateProgress(Long enrollmentId,Long courseId, Long completedActivities);
    EnrollmentProgress getEnrollmentProgress(Long enrollmentId, Long courseId);
    void cancelEnrollment(Long enrollmentId, Long courseId);
    List<EnrollmentProgress> checkEnrollmentProgress();
    String getEnrollmentNameWithId(Long enrollmentId);
}

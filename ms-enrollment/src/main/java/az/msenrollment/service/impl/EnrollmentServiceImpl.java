package az.msenrollment.service.impl;

import az.msenrollment.client.CourseDictionaryClient;
import az.msenrollment.dao.EnrollmentDetails;
import az.msenrollment.dao.EnrollmentProgress;
import az.msenrollment.dao.EnrollmentResponse;
import az.msenrollment.event.StudentEnrolledEvent;
import az.msenrollment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentServiceImpl implements EnrollmentService {

    private final CourseDictionaryClient courseDictionaryClient;
    private final KafkaTemplate<String,StudentEnrolledEvent> kafkaTemplate;

    @Autowired
    public EnrollmentServiceImpl(CourseDictionaryClient courseDictionaryClient, KafkaTemplate<String,StudentEnrolledEvent> kafkaTemplate) {
        this.courseDictionaryClient = courseDictionaryClient;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String enrollStudent(String username,Long courseId){
        courseDictionaryClient.enrollStudent(username,courseId);
        kafkaTemplate.send("notificationTopic",new StudentEnrolledEvent(username));
        return "Student enrolled course successfully";
    }

    @Override
    public ResponseEntity<List<EnrollmentResponse>> getEnrollments(){
        return courseDictionaryClient.getEnrollments();
    }

    @Override
    public ResponseEntity<EnrollmentDetails> getEnrollmentDetails(Long enrollmentId){
        return courseDictionaryClient.getEnrollmentDetails(enrollmentId);
    }

    @Override
    public void updateProgress(Long enrollmentId,Long courseId,Long completedActivities){
        courseDictionaryClient.updateProgress(enrollmentId,courseId,completedActivities);
    }

    @Override
    public ResponseEntity<EnrollmentProgress> getEnrollmentProgress(Long enrollmentId,Long courseId){
        return courseDictionaryClient.getEnrollmentProgress(enrollmentId,courseId);
    }

    @Override
    public void cancelEnrollment(Long enrollmentId,Long courseId){
        courseDictionaryClient.cancelEnrollment(enrollmentId,courseId);
    }
}

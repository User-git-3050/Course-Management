package az.msreport.service.impl;

import az.msreport.client.CourseDictionaryClient;
import az.msreport.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {

    private final CourseDictionaryClient courseDictionaryClient;

    @Autowired
    public ReportServiceImpl(CourseDictionaryClient courseDictionaryClient) {
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public ResponseEntity<String> getCourseReport(Long courseId,String username) {
        return courseDictionaryClient.getCourseReport(courseId,username);
    }

    @Override
    public ResponseEntity<String> getStudentReport(String studentName,String username) {
        return courseDictionaryClient.getStudentReport(studentName,username);
    }

    @Override
    public ResponseEntity<String> getInstructorReport(String instructorName){
        return courseDictionaryClient.getInstructorReport(instructorName);
    }
}

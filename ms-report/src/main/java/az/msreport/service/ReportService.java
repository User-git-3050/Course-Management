package az.msreport.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface ReportService {
    ResponseEntity<String> getCourseReport(Long courseId,String username);
    ResponseEntity<String> getStudentReport(String studentName,String username);
    ResponseEntity<String> getInstructorReport(String instructorName);
}

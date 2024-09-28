package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.CourseReport;
import az.mscoursedictionary.dao.InstructorReport;
import az.mscoursedictionary.dao.StudentReport;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface  ReportDictionaryService {
    String getCourseReport(Long courseId,String username);

    String getStudentReport(String enrollmentName,String username);

    String getInstructorReport(String instructorName);

    List<String> getAllCourseReports();

    List<String> getAllStudentReports();

    List<String> getAllInstructorReports();
}

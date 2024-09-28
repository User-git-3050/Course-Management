package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseDictionaryService {
    void createCourse(CourseRequest courseRequest,String instructorName);

    List<CourseResponse> getAllCourses();

    CourseResponse getCourseById(Long courseId);

    void updateCourse(Long courseId, CourseRequest courseRequest,String instructorName);

    void deleteCourseById(Long courseId);

    List<InstructorResponse> getAllInstructorsOfCourse(Long courseId);

    void addInstructorToCourse(Long courseId, String instructorName);

    List<EnrollmentsWithCourse> getCompletedCoursesWithEnrollments();

    String getCourseNameWithId(Long courseId);
}

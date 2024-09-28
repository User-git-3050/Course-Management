package az.mscourse.service;

import az.mscourse.dao.CourseRequest;
import az.mscourse.dao.CourseResponse;
import az.mscourse.dao.InstructorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CourseService {
    void createCourse(CourseRequest courseRequest,String instructorName);
    ResponseEntity<List<CourseResponse>> getAllCourses();
    ResponseEntity<CourseResponse> getCourseById(Long id);
    ResponseEntity<String> updateCourseById(Long id , CourseRequest courseRequest,String instructorName);
    ResponseEntity<String> deleteCourseById(Long id);
    ResponseEntity <List<InstructorResponse>> getAllInstructorsForCourse(Long id);
    ResponseEntity<String> addInstructorToCourse(Long courseId, String instructorName);
}

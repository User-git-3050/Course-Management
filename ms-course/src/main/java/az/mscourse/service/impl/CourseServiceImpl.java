package az.mscourse.service.impl;

import az.mscourse.client.CourseDictionaryClient;
import az.mscourse.dao.CourseRequest;
import az.mscourse.dao.CourseResponse;
import az.mscourse.dao.InstructorResponse;
import az.mscourse.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseDictionaryClient courseDictionaryClient;

    public CourseServiceImpl(CourseDictionaryClient courseDictionaryClient) {
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public void createCourse(CourseRequest courseRequest,String instructorName){
        courseDictionaryClient.createCourse(courseRequest,instructorName);
    }

    @Override
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        return courseDictionaryClient.getAllCourses();
    }

    @Override
    public ResponseEntity<CourseResponse> getCourseById(Long id){
        return courseDictionaryClient.getCourseById(id);
    }

    @Override
    public ResponseEntity<String> deleteCourseById(Long id){
        return courseDictionaryClient.deleteCourseById(id);
    }

    @Override
    public ResponseEntity<String> updateCourseById(Long id, CourseRequest courseRequest,String instructorName){
        return courseDictionaryClient.updateCourse(id,courseRequest,instructorName);
    }

    @Override
    public ResponseEntity<List<InstructorResponse>> getAllInstructorsForCourse(Long courseId){
        return courseDictionaryClient.getAllInstructorsOfCourse(courseId);
    }

    @Override
    public ResponseEntity<String> addInstructorToCourse(Long courseId,String instructorName){
        return courseDictionaryClient.addInstructorToCourse(courseId,instructorName);
    }
}

package az.mscourse.controller;

import az.mscourse.dao.CourseRequest;
import az.mscourse.dao.CourseResponse;
import az.mscourse.dao.InstructorResponse;
import az.mscourse.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses/")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping
    public void createCourse(@RequestBody CourseRequest courseRequest, @RequestHeader String username){
        courseService.createCourse(courseRequest,username);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        return courseService.getAllCourses();
    }

    @GetMapping("{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId){
        return courseService.getCourseById(courseId);
    }

    @PutMapping("{courseId}")
    public ResponseEntity<String> updateCourseById(@PathVariable Long courseId, @RequestBody CourseRequest courseRequest,@RequestHeader String username){
        return courseService.updateCourseById(courseId,courseRequest,username);
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long courseId){
        return courseService.deleteCourseById(courseId);
    }

    @GetMapping("{courseId}/instructors")
    public ResponseEntity<List<InstructorResponse>> getAllInstructorsForCourse(@PathVariable Long courseId){
        return courseService.getAllInstructorsForCourse(courseId);
    }

    @PostMapping("{courseId}/instructors")
    public ResponseEntity<String> addInstructorToCourse(@PathVariable Long courseId, @RequestParam String instructorName){
        return courseService.addInstructorToCourse(courseId,instructorName);
    }


}

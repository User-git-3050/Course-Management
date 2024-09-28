package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.*;
import az.mscoursedictionary.service.CourseDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static az.mscoursedictionary.enums.Info.*;

@RestController
@RequestMapping("/api/course/dictionary")
public class CourseController {
    private final CourseDictionaryService courseDictionaryService;

    @Autowired
    public CourseController(CourseDictionaryService courseDictionaryService) {
        this.courseDictionaryService = courseDictionaryService;
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody CourseRequest courseRequest,@RequestParam String instructorName) {
        courseDictionaryService.createCourse(courseRequest,instructorName);
        return ResponseEntity.ok(COURSE_WAS_CREATED.getMessage());
    }

    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        return ResponseEntity.ok(courseDictionaryService.getAllCourses());
    }

    @GetMapping("{courseId}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseDictionaryService.getCourseById(courseId));
    }

    @PutMapping("{courseId}")
    public ResponseEntity<String> updateCourse(@PathVariable Long courseId, @RequestBody  CourseRequest courseRequest,@RequestParam String instructorName) {
        courseDictionaryService.updateCourse(courseId, courseRequest,instructorName);
        return ResponseEntity.ok(COURSE_WAS_MODIFIED.getMessage());
    }

    @DeleteMapping("{courseId}")
    public ResponseEntity<String> deleteCourseById(@PathVariable Long courseId) {
        courseDictionaryService.deleteCourseById(courseId);
        return ResponseEntity.ok(COURSE_WAS_DELETED.getMessage());
    }

    @GetMapping("{courseId}/instructors")
    public ResponseEntity<List<InstructorResponse>>getAllInstructorsOfCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseDictionaryService.getAllInstructorsOfCourse(courseId));
    }

    @PostMapping("{courseId}/instructors")
    public ResponseEntity<String> addInstructorToCourse(@PathVariable Long courseId, @RequestParam String instructorName) {
        courseDictionaryService.addInstructorToCourse(courseId, instructorName);
        return ResponseEntity.ok(INSTRUCTOR_WAS_ADDED.getMessage());
    }

    @GetMapping("completed")
    public ResponseEntity<List<EnrollmentsWithCourse>> getCompletedCoursesWithEnrollments(){
        return ResponseEntity.ok(courseDictionaryService.getCompletedCoursesWithEnrollments());
    }

    @GetMapping("{courseId}/name")
    public ResponseEntity<String> getCourseNameWithId(@PathVariable Long courseId) {
        return ResponseEntity.ok(courseDictionaryService.getCourseNameWithId(courseId));
    }



}

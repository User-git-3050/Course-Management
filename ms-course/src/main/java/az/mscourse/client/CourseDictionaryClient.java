package az.mscourse.client;

import az.mscourse.dao.CourseRequest;
import az.mscourse.dao.CourseResponse;
import az.mscourse.dao.InstructorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {
    @PostMapping("api/course/dictionary")
    ResponseEntity<String> createCourse(@RequestBody CourseRequest courseRequest, @RequestParam String instructorName);

    @GetMapping("api/course/dictionary")
    ResponseEntity<List<CourseResponse>> getAllCourses();

    @GetMapping("api/course/dictionary/{courseId}")
    ResponseEntity<CourseResponse> getCourseById(@PathVariable Long courseId);

    @PutMapping("api/course/dictionary/{courseId}")
    ResponseEntity<String> updateCourse(@PathVariable Long courseId,@RequestBody CourseRequest courseRequest,@RequestParam String instructorName);

    @DeleteMapping("api/course/dictionary/{courseId}")
    ResponseEntity<String> deleteCourseById(@PathVariable Long courseId);

    @GetMapping("api/course/dictionary/{courseId}/instructors")
    ResponseEntity<List<InstructorResponse>> getAllInstructorsOfCourse(@PathVariable Long courseId);

    @PostMapping("api/course/dictionary/{courseId}/instructors")
    ResponseEntity<String> addInstructorToCourse(@PathVariable Long courseId,@RequestParam String instructorName);


}

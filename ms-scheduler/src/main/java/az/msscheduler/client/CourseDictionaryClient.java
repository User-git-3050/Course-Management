package az.msscheduler.client;

import az.msscheduler.dao.response.InstructorResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {
    @GetMapping("api/reports/dictionary/courses/all")
    ResponseEntity<List<String>> getAllCourseReport();

    @GetMapping("api/reports/dictionary/students/all")
    ResponseEntity<List<String>> getAllStudentReport();

    @GetMapping("api/reports/dictionary/instructors/all")
    ResponseEntity<List<String>> getAllInstructorReport();

    @GetMapping("api/user/dictionary/email-admin")
    ResponseEntity<List<String>> getAdminEmails();

    @GetMapping("api/user/dictionary/instructor")
    ResponseEntity<List<InstructorResponse>> getInstructors();

    @GetMapping("courses")
    ResponseEntity<String> getCourseReport(@RequestParam Long courseId,@RequestParam String username);


    @GetMapping("students")
    ResponseEntity<String> getStudentReport(@RequestParam String studentName,@RequestParam String username);



}

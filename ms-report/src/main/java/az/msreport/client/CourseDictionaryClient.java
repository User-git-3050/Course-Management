package az.msreport.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {

    @GetMapping("api/reports/dictionary/courses")
    ResponseEntity<String> getCourseReport(@RequestParam Long courseId,@RequestParam String username);

    @GetMapping("api/reports/dictionary/students")
    ResponseEntity<String> getStudentReport(@RequestParam String studentName, @RequestParam String username);

    @GetMapping("api/reports/dictionary/instructors")
    ResponseEntity<String> getInstructorReport(@RequestParam String instructorName);
}

package az.msauthcourse.client;

import az.msauthcourse.dao.InstructorRegistrationRequest;
import az.msauthcourse.dao.StudentRegistrationRequest;
import az.msauthcourse.dao.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {
    @GetMapping("api/user/dictionary/{username}")
    ResponseEntity<UserResponse> getUserByUsername(@PathVariable("username") String username);

    @GetMapping("api/user/dictionary/{email}/{username}")
    ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username);

    @PostMapping("api/user/dictionary/student")
    String registerStudent(@RequestBody StudentRegistrationRequest studentRegistrationRequest);

    @PostMapping("api/user/dictionary/instructor")
    String registerInstructor(@RequestBody InstructorRegistrationRequest instructorRegistrationRequest);
}

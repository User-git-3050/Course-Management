package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.*;
import az.mscoursedictionary.enums.Info;
import az.mscoursedictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/dictionary")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("email-admin")
    public ResponseEntity<List<String>> getAdminEmails(){
        return ResponseEntity.ok(userService.getAdminEmails());
    }

    @GetMapping("instructor")
    public ResponseEntity<List<InstructorResponse>> getInstructors(){
        return ResponseEntity.ok(userService.getInstructors());
    }

    @GetMapping("{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username){
        return ResponseEntity.ok(userService.findUserByUsername(username));
    }

    @PostMapping("student")
    public String registerStudent(@RequestBody StudentRegistrationRequest studentRegistrationRequest){
        userService.saveStudent(studentRegistrationRequest);
        return Info.STUDENT_REGISTERED.getMessage();
    }

    @PostMapping("instructor")
    public String registerInstructor(@RequestBody InstructorRegistrationRequest instructorRegistrationRequest){
        userService.saveInstructor(instructorRegistrationRequest);
        return Info.INSTRUCTOR_REGISTERED.getMessage();
    }

    @GetMapping("/{email}/{username}")
    ResponseEntity<UserResponse> getUserByEmailOrUsername(@PathVariable String email, @PathVariable String username){
        return ResponseEntity.ok(userService.findUserByUsernameOrEmail(email,username));
    }



}

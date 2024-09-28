package az.mscoursedictionary.service;

import az.mscoursedictionary.dao.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<String> getAdminEmails();

    List<InstructorResponse> getInstructors();

    UserResponse findUserByUsername(String username);

    void saveStudent(StudentRegistrationRequest studentRegistrationRequest);

    void saveInstructor(InstructorRegistrationRequest instructorRegistrationRequest);

    UserResponse  findUserByUsernameOrEmail(String email, String username);
}

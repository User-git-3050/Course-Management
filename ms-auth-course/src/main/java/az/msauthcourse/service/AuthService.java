package az.msauthcourse.service;

import az.msauthcourse.dao.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    String registerInstructor(InstructorRegistrationRequest instructorRegistrationRequest);

    JwtResponse generateToken(AuthRequest authRequest);

    String registerStudent(StudentRegistrationRequest studentRegistrationRequest);
}

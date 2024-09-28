package az.msauthcourse.controller;

import az.msauthcourse.dao.*;
import az.msauthcourse.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("login")
    public JwtResponse login(@RequestBody AuthRequest authRequest){
        return authService.generateToken(authRequest);
    }

    @PostMapping("register/student")
    public String registerStudent(@RequestBody StudentRegistrationRequest studentRegistrationRequest){
        return authService.registerStudent(studentRegistrationRequest);
    }
    
    @PostMapping("register/instructor")
    public String registerInstructor(@RequestBody InstructorRegistrationRequest instructorRegistrationRequest){
        return authService.registerInstructor(instructorRegistrationRequest);
    }


}

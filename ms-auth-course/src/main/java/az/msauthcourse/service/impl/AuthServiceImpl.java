package az.msauthcourse.service.impl;

import az.msauthcourse.client.CourseDictionaryClient;
import az.msauthcourse.dao.*;
import az.msauthcourse.security.JwtUtil;
import az.msauthcourse.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final CourseDictionaryClient courseDictionaryClient;

    @Autowired
    public AuthServiceImpl(PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager, CourseDictionaryClient courseDictionaryClient) {
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public JwtResponse generateToken(AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtUtil.generateToken(authentication);
            return JwtResponse.builder().token(token).build();
        } else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }

    @Override
    public String registerStudent(StudentRegistrationRequest studentRegistrationRequest) {
        UserResponse existingUser = courseDictionaryClient.getUserByEmailOrUsername(studentRegistrationRequest.getEmail(),
                studentRegistrationRequest.getUsername()).getBody();

        if (existingUser != null && existingUser.getEmail() != null && existingUser.getUsername() != null) {
            return "User already exists";
        }

        studentRegistrationRequest.setPassword(passwordEncoder.encode(studentRegistrationRequest.getPassword()));
        return courseDictionaryClient.registerStudent(studentRegistrationRequest);

    }

    @Override
    public String registerInstructor(InstructorRegistrationRequest instructorRegistrationRequest) {
        UserResponse existingUser = courseDictionaryClient.getUserByEmailOrUsername(instructorRegistrationRequest.getEmail(),
                instructorRegistrationRequest.getUsername()).getBody();

        if (existingUser != null && existingUser.getEmail() != null && existingUser.getUsername() != null) {
            return "User already exists";
        }

        instructorRegistrationRequest.setPassword(passwordEncoder.encode(instructorRegistrationRequest.getPassword()));
        return courseDictionaryClient.registerInstructor(instructorRegistrationRequest);
    }

}

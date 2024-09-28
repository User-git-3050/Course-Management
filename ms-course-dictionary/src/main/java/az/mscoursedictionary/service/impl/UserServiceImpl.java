package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.*;
import az.mscoursedictionary.entity.EnrollmentEntity;
import az.mscoursedictionary.entity.InstructorEntity;
import az.mscoursedictionary.entity.UserEntity;
import az.mscoursedictionary.enums.RoleEnum;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.EnrollmentRepository;
import az.mscoursedictionary.repository.InstructorRepository;
import az.mscoursedictionary.repository.UserRepository;
import az.mscoursedictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.mscoursedictionary.enums.ErrorMessages.USER_NOT_FOUND;
import static az.mscoursedictionary.mapper.EnrollmentMapper.ENROLLMENT_MAPPER;
import static az.mscoursedictionary.mapper.InstructorMapper.INSTRUCTOR_MAPPER;
import static az.mscoursedictionary.mapper.UserMapper.*;
import static java.lang.String.format;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, EnrollmentRepository enrollmentRepository, InstructorRepository instructorRepository) {
        this.userRepository = userRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public List<String> getAdminEmails(){
        return userRepository.findAllByRoleIsAdmin();
    }

    @Override
    public List<InstructorResponse> getInstructors(){
        return userRepository.findAllByRoleIsInstructor().stream().map(INSTRUCTOR_MAPPER::mapToResponse).toList();
    }

    @Override
    public UserResponse findUserByUsername(String username){
        return userRepository.findByUsername(username).map(USER_MAPPER::mapToResponse).
                orElseThrow(()->new NotFoundException(
                        format(
                                USER_NOT_FOUND.getMessage(),
                                username
                        )
                ));
    }

    @Override
    public void saveStudent(StudentRegistrationRequest studentRegistrationRequest){
        EnrollmentEntity enrollmentEntity = EnrollmentEntity.builder()
                .username(studentRegistrationRequest.getUsername())
                .user(UserEntity.builder()
                        .username(studentRegistrationRequest.getUsername())
                        .password(studentRegistrationRequest.getPassword())
                        .name(studentRegistrationRequest.getName())
                        .surname(studentRegistrationRequest.getSurname())
                        .email(studentRegistrationRequest.getEmail())
                        .phone(studentRegistrationRequest.getPhone())
                        .role(RoleEnum.STUDENT)
                        .build())
                .build();

        enrollmentRepository.save(enrollmentEntity);





    }

    @Override
    public void saveInstructor(InstructorRegistrationRequest instructorRegistrationRequest){
        InstructorEntity instructorEntity = InstructorEntity.builder()
                .username(instructorRegistrationRequest.getUsername())
                .subject(instructorRegistrationRequest.getSubject())
                .courseAmount(0L)
                .updateAmount(0L)
                .numberOfStudents(0L)
                .user(UserEntity.builder()
                        .username(instructorRegistrationRequest.getUsername())
                        .name(instructorRegistrationRequest.getName())
                        .surname(instructorRegistrationRequest.getSurname())
                        .password(instructorRegistrationRequest.getPassword())
                        .email(instructorRegistrationRequest.getEmail())
                        .phone(instructorRegistrationRequest.getPhone())
                        .role(RoleEnum.INSTRUCTOR)
                        .build())
                .build();

        instructorRepository.save(instructorEntity);

    }

    @Override
    public UserResponse findUserByUsernameOrEmail(String email,String username){
        return userRepository.findByEmailOrUsername(email,username).map(USER_MAPPER::mapToResponse)
                .orElse(null);
    }


}

package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.CourseReport;
import az.mscoursedictionary.dao.InstructorReport;
import az.mscoursedictionary.dao.StudentReport;
import az.mscoursedictionary.entity.*;
import az.mscoursedictionary.enums.ErrorMessages;
import az.mscoursedictionary.enums.RoleEnum;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.*;
import az.mscoursedictionary.service.ReportDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static az.mscoursedictionary.enums.ErrorMessages.*;
import static az.mscoursedictionary.mapper.CourseMapper.COURSE_MAPPER;
import static az.mscoursedictionary.mapper.ReportMapper.REPORT_MAPPER;
import static java.lang.String.format;

@Service
public class ReportDictionaryServiceImpl implements ReportDictionaryService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final InstructorRepository instructorRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReportDictionaryServiceImpl(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, InstructorRepository instructorRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.instructorRepository = instructorRepository;
        this.userRepository = userRepository;
    }

    @Override
    public String getCourseReport(Long courseId, String username) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));


        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                USER_NOT_FOUND.getMessage(),
                                username
                        )
                ));

        switch (user.getRole()) {
            case ADMIN -> {
                return REPORT_MAPPER.mapToCourseReport(courseEntity).toString();
            }
            case INSTRUCTOR -> {
                if (user.getInstructor().getCourses().contains(courseEntity)) {
                    return REPORT_MAPPER.mapToCourseReport(courseEntity).toString();
                }
            }
        }

        return null;

    }

    @Override
    public String getStudentReport(String enrollmentName, String username) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUsername(enrollmentName)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_USERNAME.getMessage(),
                                enrollmentName
                        )
                ));

        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                USER_NOT_FOUND.getMessage(),
                                username
                        )
                ));

        switch (user.getRole()) {
            case ADMIN -> {
                return REPORT_MAPPER.mapToStudentReport(enrollmentEntity).toString();
            }
            case INSTRUCTOR -> {
                boolean hasInstructorWithUsername = enrollmentEntity.getEnrollmentDetails().stream()
                        .flatMap(enrollmentDetailsEntity -> enrollmentDetailsEntity.getCourse().getInstructors().stream())
                        .anyMatch(instructorEntity -> instructorEntity.getUsername().equals(username));
                if (hasInstructorWithUsername) {
                    return REPORT_MAPPER.mapToStudentReport(enrollmentEntity).toString();
                }
            }
        }

        return null;

    }

    @Override
    public String getInstructorReport(String instructorName) {
        InstructorEntity instructorEntity = instructorRepository.findByUsername(instructorName)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                INSTRUCTOR_NOT_FOUND_WITH_USERNAME.getMessage(),
                                instructorName
                        )
                ));

        return REPORT_MAPPER.mapToInstructorReport(instructorEntity).toString();
    }

    @Override
    public List<String> getAllCourseReports() {
        List<String> courseReports = new ArrayList<>();

        courseRepository.findAll().forEach((courseEntity) -> {
            courseReports.add(REPORT_MAPPER.mapToCourseReport(courseEntity).toString());
        });

        return courseReports;
    }

    @Override
    public List<String> getAllStudentReports() {
        List<String> studentReports = new ArrayList<>();

        enrollmentRepository.findAll().forEach((enrollmentEntity) -> {
            studentReports.add(REPORT_MAPPER.mapToStudentReport(enrollmentEntity).toString());
        });

        return studentReports;
    }

    @Override
    public List<String> getAllInstructorReports() {
        List<String> instructorReports = new ArrayList<>();

        instructorRepository.findAll().forEach((instructorEntity) -> {
            instructorReports.add(REPORT_MAPPER.mapToInstructorReport(instructorEntity).toString());
        });

        return instructorReports;
    }


}

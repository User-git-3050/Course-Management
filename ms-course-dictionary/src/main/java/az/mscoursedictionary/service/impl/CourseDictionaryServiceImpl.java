package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.*;
import az.mscoursedictionary.entity.CourseEntity;
import az.mscoursedictionary.entity.InstructorEntity;
import az.mscoursedictionary.enums.EnrollmentStatus;
import az.mscoursedictionary.enums.Info;
import az.mscoursedictionary.exception.MethodNotAllowedException;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.EnrollmentDetailsRepository;
import az.mscoursedictionary.repository.CourseRepository;
import az.mscoursedictionary.repository.InstructorRepository;
import az.mscoursedictionary.service.CourseDictionaryService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.mscoursedictionary.enums.ErrorMessages.*;
import static az.mscoursedictionary.mapper.CourseMapper.COURSE_MAPPER;
import static az.mscoursedictionary.mapper.InstructorMapper.INSTRUCTOR_MAPPER;
import static java.lang.String.format;

@Service
public class CourseDictionaryServiceImpl implements CourseDictionaryService {

    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final EnrollmentDetailsRepository enrollmentDetailsRepository;

    @Autowired
    public CourseDictionaryServiceImpl(CourseRepository courseRepository, InstructorRepository instructorRepository, EnrollmentDetailsRepository enrollmentDetailsRepository) {
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
        this.enrollmentDetailsRepository = enrollmentDetailsRepository;
    }

    @Override
    public void createCourse(CourseRequest courseRequest,String instructorName) {
        InstructorEntity instructorEntity = instructorRepository.findByUsername(instructorName)
                .orElseThrow(()-> new NotFoundException(
                        format(
                                INSTRUCTOR_NOT_FOUND_WITH_USERNAME.getMessage(),
                                instructorName
                        )
                ));
        courseRepository.save(COURSE_MAPPER.mapToEntity(courseRequest));

        instructorEntity.setCourseAmount(instructorEntity.getCourseAmount()+1);
        instructorEntity.getCourses().add(COURSE_MAPPER.mapToEntity(courseRequest));
        instructorRepository.save(instructorEntity);



    }

    @Override
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll().stream().map(COURSE_MAPPER::mapToResponse).toList();
    }

    @Override
    public CourseResponse getCourseById(Long courseId) {
        return COURSE_MAPPER.mapToResponse(courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ))
        );
    }

    @Override
    public void updateCourse(Long courseId, CourseRequest courseRequest, String instructorName) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));
        InstructorEntity instructorEntity = instructorRepository.findByUsername(instructorName)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                INSTRUCTOR_NOT_FOUND_WITH_USERNAME.getMessage(),
                                instructorName
                        )
                ));

        if (instructorEntity.getCourses().contains(courseEntity)) {
            instructorEntity.setUpdateAmount(instructorEntity.getUpdateAmount() + 1);
            courseEntity.setName(courseRequest.getName() == null ? courseEntity.getName() : courseRequest.getName());
            courseEntity.setDescription(courseRequest.getDescription() == null ? courseEntity.getDescription() : courseRequest.getDescription());
            courseEntity.setCategoryName(courseRequest.getCategoryName() == null ? courseEntity.getCategoryName() : courseRequest.getCategoryName());
            courseEntity.setPrice(courseRequest.getPrice() == null ? courseEntity.getPrice() : courseRequest.getPrice());
            courseEntity.setTotalActivities(courseRequest.getTotalActivities() == null ? courseEntity.getTotalActivities() : courseRequest.getTotalActivities());
            courseRepository.save(courseEntity);
            instructorRepository.save(instructorEntity);
        }
        else{
            throw new MethodNotAllowedException(
                    format(
                            METHOD_NOT_ALLOWED.getMessage(),
                            courseEntity.getId()
                    )
            );
        }

    }
    @Override
    public void deleteCourseById(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));
        courseRepository.delete(courseEntity);
    }

    @Override
    public List<InstructorResponse> getAllInstructorsOfCourse(Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));
        return courseEntity.getInstructors().stream().map(INSTRUCTOR_MAPPER::mapToResponse).toList();
    }

    @Override
    public void addInstructorToCourse(Long courseId, String instructorName) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));
        InstructorEntity instructorEntity = instructorRepository.findByUsername(instructorName)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                INSTRUCTOR_NOT_FOUND_WITH_USERNAME.getMessage(),
                                instructorName
                        )
                ));
        courseEntity.getInstructors().add(instructorEntity);
        courseRepository.save(courseEntity);

    }

    @Override
    public List<EnrollmentsWithCourse> getCompletedCoursesWithEnrollments(){
        return enrollmentDetailsRepository.findAllByStatusIs(EnrollmentStatus.COMPLETED)
                .stream()
                .map(enrollmentDetails-> EnrollmentsWithCourse.builder()
                        .enrollmentId(enrollmentDetails.getEnrollment().getId())
                        .courseId(enrollmentDetails.getCourse().getId())
                        .courseName(enrollmentDetails.getCourse().getName())
                        .enrollmentName(enrollmentDetails.getEnrollment().getUsername())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public String getCourseNameWithId(Long courseId){
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));
        return courseEntity.getName();
    }





}

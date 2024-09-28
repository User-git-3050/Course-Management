package az.mscoursedictionary.service.impl;

import az.mscoursedictionary.dao.EnrollmentDetailsResponse;
import az.mscoursedictionary.dao.EnrollmentProgress;
import az.mscoursedictionary.dao.EnrollmentResponse;
import az.mscoursedictionary.entity.EnrollmentDetailsEntity;
import az.mscoursedictionary.entity.CourseEntity;
import az.mscoursedictionary.entity.EnrollmentEntity;
import az.mscoursedictionary.entity.InstructorEntity;
import az.mscoursedictionary.enums.EnrollmentStatus;
import az.mscoursedictionary.exception.EnrollmentAddedOnce;
import az.mscoursedictionary.exception.NotFoundException;
import az.mscoursedictionary.repository.EnrollmentDetailsRepository;
import az.mscoursedictionary.repository.CourseRepository;
import az.mscoursedictionary.repository.EnrollmentRepository;
import az.mscoursedictionary.repository.InstructorRepository;
import az.mscoursedictionary.service.EnrollmentDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static az.mscoursedictionary.enums.ErrorMessages.*;
import static az.mscoursedictionary.mapper.EnrollmentMapper.ENROLLMENT_MAPPER;
import static java.lang.String.format;

@Service
public class EnrollmentDictionaryServiceImpl implements EnrollmentDictionaryService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentDetailsRepository enrollmentDetailsRepository;
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;

    @Autowired
    public EnrollmentDictionaryServiceImpl(EnrollmentRepository enrollmentRepository, EnrollmentDetailsRepository enrollmentDetailsRepository, CourseRepository courseRepository, InstructorRepository instructorRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentDetailsRepository = enrollmentDetailsRepository;
        this.courseRepository = courseRepository;
        this.instructorRepository = instructorRepository;
    }

    @Override
    public void enrollStudent(String username, Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId).
                orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId

                        )
                ));

        EnrollmentEntity enrollmentEntity = enrollmentRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_USERNAME.getMessage(),
                                username
                        )
                ));


            boolean exists = enrollmentEntity.getEnrollmentDetails().stream()
                    .anyMatch(enrollmentDetails->enrollmentDetails.getCourse()==courseEntity);

            if(!exists) {


                List<InstructorEntity> instructors = courseEntity.getInstructors();
                instructors.forEach(instructor -> {
                            instructor.setNumberOfStudents(instructor.getNumberOfStudents() + 1);
                            instructorRepository.save(instructor);
                        }
                );
                EnrollmentDetailsEntity enrollmentDetailsEntity = EnrollmentDetailsEntity.builder()
                        .course(courseEntity)
                        .enrollment(enrollmentEntity)
                        .enrollmentDate(LocalDateTime.now())
                        .status(EnrollmentStatus.ACTIVE)
                        .completedActivities(0L)
                        .progress(0L)
                        .build();

                enrollmentDetailsRepository.save(enrollmentDetailsEntity);
            }
            else{
                throw new EnrollmentAddedOnce(ENROLLMENT_ADDED_ONLY_ONCE.getMessage());
            }


    }

    @Override
    public List<EnrollmentResponse> getAllEnrollments() {
        return enrollmentRepository.findAll().stream().map(ENROLLMENT_MAPPER::mapToResponse).toList();
    }

    @Override
    public EnrollmentDetailsResponse getEnrollmentDetails(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId).map(ENROLLMENT_MAPPER::mapToDetails)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND.getMessage(),
                                enrollmentId

                        )
                ));
    }

    @Override
    public void updateProgress(Long enrollmentId, Long courseId, Long completedActivities) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));

        EnrollmentDetailsEntity enrollmentDetailsEntity = enrollmentDetailsRepository.findByCourseIdAndEnrollmentId(enrollmentId, courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_COURSE.getMessage(),
                                courseId
                        )
                ));

        if(enrollmentDetailsEntity.getCourse().getTotalActivities()>=completedActivities && enrollmentDetailsEntity.getStatus().equals(EnrollmentStatus.ACTIVE)) {
            Long totalActivities = courseEntity.getTotalActivities();
            enrollmentDetailsEntity.setCompletedActivities(completedActivities + enrollmentDetailsEntity.getCompletedActivities());
            enrollmentDetailsEntity.setProgress((enrollmentDetailsEntity.getCompletedActivities() * 100 / totalActivities));
            if(enrollmentDetailsEntity.getProgress()==100){
                enrollmentDetailsEntity.setStatus(EnrollmentStatus.COMPLETED);
                enrollmentDetailsEntity.setCompletionDate(LocalDateTime.now());
            }
            enrollmentDetailsRepository.save(enrollmentDetailsEntity);
        }



    }

    @Override
    public EnrollmentProgress getEnrollmentProgress(Long enrollmentId, Long courseId) {
        CourseEntity courseEntity = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                COURSE_NOT_FOUND.getMessage(),
                                courseId
                        )
                ));

        EnrollmentDetailsEntity enrollmentDetailsEntity = enrollmentDetailsRepository.findByCourseIdAndEnrollmentId(enrollmentId, courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_COURSE.getMessage(),
                                courseId
                        )
                ));
        return EnrollmentProgress.builder()
                .completionDate(enrollmentDetailsEntity.getCompletionDate())
                .totalActivities(courseEntity.getTotalActivities())
                .completedActivities(enrollmentDetailsEntity.getCompletedActivities())
                .enrollmentId(enrollmentId)
                .courseId(courseId)
                .courseName(enrollmentDetailsEntity.getCourse().getName())
                .build();
    }

    @Override
    public void cancelEnrollment(Long enrollmentId, Long courseId) {
        EnrollmentDetailsEntity enrollmentDetailsEntity = enrollmentDetailsRepository.findByCourseIdAndEnrollmentId(enrollmentId, courseId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND_WITH_COURSE.getMessage(),
                                courseId
                        )
                ));

        assert enrollmentDetailsEntity.getStatus().equals(EnrollmentStatus.ACTIVE);

        enrollmentDetailsEntity.setStatus(EnrollmentStatus.CANCELLED);
        enrollmentDetailsRepository.save(enrollmentDetailsEntity);


    }

    @Override
    public String getEnrollmentNameWithId(Long enrollmentId) {
        EnrollmentEntity enrollmentEntity = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new NotFoundException(
                        format(
                                ENROLLMENT_NOT_FOUND.getMessage(),
                                enrollmentId
                        )
                ));
        return enrollmentEntity.getUsername();
    }


    @Override
    public List<EnrollmentProgress> checkEnrollmentProgress() {
        List<Long> progress = Arrays.asList(25L, 50L, 75L);
        List<EnrollmentDetailsEntity> enrollmentDetailsEntities = enrollmentDetailsRepository.findAllByProgressIsIn(progress).orElse(null);
        assert enrollmentDetailsEntities != null;
        return enrollmentDetailsEntities.stream()
                .map(enrollmentDetailsEntity -> EnrollmentProgress.builder()
                        .enrollmentName(enrollmentDetailsEntity.getEnrollment().getUsername())
                        .enrollmentId(enrollmentDetailsEntity.getEnrollment().getId())
                        .courseId(enrollmentDetailsEntity.getCourse().getId())
                        .completionDate(enrollmentDetailsEntity.getCompletionDate())
                        .totalActivities(enrollmentDetailsEntity.getCourse().getTotalActivities())
                        .completedActivities(enrollmentDetailsEntity.getCompletedActivities())
                        .progress(enrollmentDetailsEntity.getProgress())
                        .courseName(enrollmentDetailsEntity.getCourse().getName())
                        .build()
                )
                .toList();

    }


}

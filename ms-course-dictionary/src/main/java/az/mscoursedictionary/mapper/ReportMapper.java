package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.CourseReport;
import az.mscoursedictionary.dao.InstructorReport;
import az.mscoursedictionary.dao.InstructorResponse;
import az.mscoursedictionary.dao.StudentReport;
import az.mscoursedictionary.entity.*;
import az.mscoursedictionary.enums.EnrollmentStatus;

import java.util.List;
import java.util.stream.Collectors;

import static az.mscoursedictionary.mapper.CourseMapper.COURSE_MAPPER;
import static az.mscoursedictionary.mapper.EnrollmentMapper.ENROLLMENT_MAPPER;
import static az.mscoursedictionary.mapper.InstructorMapper.INSTRUCTOR_MAPPER;

public enum ReportMapper {
    REPORT_MAPPER;

    public CourseReport mapToCourseReport(CourseEntity courseEntity) {

        List<EnrollmentDetailsEntity> completedEnrollments = courseEntity.getEnrollmentDetails()
                .stream()
                .filter(enrollmentDetailsEntity -> enrollmentDetailsEntity.getStatus() == EnrollmentStatus.COMPLETED)
                .toList();


        return CourseReport.builder()
                .completedEnrollments(
                        completedEnrollments.stream()
                                .map(completedEnrollment->completedEnrollment.getEnrollment().getUsername())
                                .toList()


                )
                .courseName(courseEntity.getName())
                .enrollments(
                        courseEntity.getEnrollmentDetails().stream()
                                .map(enrollmentDetailsEntity -> enrollmentDetailsEntity.getEnrollment().getUsername())
                                .toList()
                )
                .instructors(
                        courseEntity.getInstructors().stream()
                                .map(InstructorEntity::getUsername)
                                .toList()
                )
                .build();
    }


    public StudentReport mapToStudentReport(EnrollmentEntity enrollmentEntity) {
        return StudentReport.builder()
                .username(enrollmentEntity.getUsername())
                .completedActivities(
                        enrollmentEntity.getEnrollmentDetails()
                                .stream()
                                .collect(Collectors.toMap(
                                        EnrollmentDetailsEntity-> EnrollmentDetailsEntity.getCourse().getName()+
                                                " id :"+EnrollmentDetailsEntity.getEnrollment().getId(),
                                        EnrollmentDetailsEntity::getCompletedActivities
                                ))
                )
                .progress(
                        enrollmentEntity.getEnrollmentDetails()
                                .stream()
                                .collect(Collectors.toMap(
                                        EnrollmentDetailsEntity-> EnrollmentDetailsEntity.getCourse().getName()+
                                                " id :"+EnrollmentDetailsEntity.getEnrollment().getId(),
                                        EnrollmentDetailsEntity::getProgress

                                ))
                )
                .certificationTitle(
                        enrollmentEntity.getCertifications().stream()
                                .map(CertificationEntity::getTitle)
                                .toList()
                )
                .build();
    }

    public InstructorReport mapToInstructorReport(InstructorEntity instructorEntity) {
        return InstructorReport.builder()
                .username(instructorEntity.getUsername())
                .courseAmount(instructorEntity.getCourseAmount())
                .updateAmount(instructorEntity.getUpdateAmount())
                .numberOfStudents(instructorEntity.getNumberOfStudents())
                .courses(
                        instructorEntity.getCourses().stream()
                                .map(CourseEntity::getName)
                                .toList()
                )
                .build();
    }
}

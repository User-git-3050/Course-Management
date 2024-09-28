package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.CourseRequest;
import az.mscoursedictionary.dao.CourseResponse;
import az.mscoursedictionary.entity.CertificationEntity;
import az.mscoursedictionary.entity.CourseEntity;
import az.mscoursedictionary.entity.InstructorEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum CourseMapper {
    COURSE_MAPPER;

    public CourseEntity mapToEntity(CourseRequest courseRequest) {
        return CourseEntity.builder().
                name(courseRequest.getName()).
                description(courseRequest.getDescription()).
                categoryName(courseRequest.getCategoryName())
                .totalActivities(0L)
                .price(courseRequest.getPrice())
                .build();
    }
    public CourseResponse mapToResponse(CourseEntity courseEntity){
        return CourseResponse.builder().
                id(courseEntity.getId()).
                name(courseEntity.getName()).
                description(courseEntity.getDescription()).
                categoryName(courseEntity.getCategoryName()).
                price(courseEntity.getPrice()).
                instructors(courseEntity.getInstructors().stream().map(InstructorEntity::getUsername).toList()).
                enrollments(courseEntity.getEnrollmentDetails().stream().map(enrollment-> enrollment.getEnrollment().getUsername()).toList()).
                build();
    }
}

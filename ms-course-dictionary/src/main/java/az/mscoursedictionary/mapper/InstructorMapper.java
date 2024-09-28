package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.InstructorRegistrationRequest;
import az.mscoursedictionary.dao.InstructorResponse;
import az.mscoursedictionary.entity.CourseEntity;
import az.mscoursedictionary.entity.InstructorEntity;

import static az.mscoursedictionary.mapper.CourseMapper.COURSE_MAPPER;

public enum InstructorMapper {
    INSTRUCTOR_MAPPER;

    public InstructorResponse mapToResponse(InstructorEntity instructorEntity){
        return InstructorResponse.builder()
                .id(instructorEntity.getId())
                .username(instructorEntity.getUsername())
                .subject(instructorEntity.getSubject())
                .courseAmount(instructorEntity.getCourseAmount())
                .updateAmount(instructorEntity.getUpdateAmount())
                .numberOfStudents(instructorEntity.getNumberOfStudents())
                .email(instructorEntity.getUser().getEmail())
                .courses(instructorEntity.getCourses().stream().map(COURSE_MAPPER::mapToResponse).toList())
                .build();
    }

    public InstructorEntity mapToEntity(InstructorRegistrationRequest instructorRegistrationRequest){
        return InstructorEntity.builder()
                .username(instructorRegistrationRequest.getUsername())
                .subject(instructorRegistrationRequest.getSubject())
                .courseAmount(0L)
                .updateAmount(0L)
                .numberOfStudents(0L)
                .build();
    }
}

package az.mscoursedictionary.mapper;

import az.mscoursedictionary.dao.EnrollmentRequest;
import az.mscoursedictionary.dao.EnrollmentDetailsResponse;
import az.mscoursedictionary.dao.EnrollmentResponse;
import az.mscoursedictionary.dao.StudentRegistrationRequest;
import az.mscoursedictionary.entity.CertificationEntity;
import az.mscoursedictionary.entity.EnrollmentDetailsEntity;
import az.mscoursedictionary.entity.EnrollmentEntity;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public enum EnrollmentMapper {
    ENROLLMENT_MAPPER;

    public EnrollmentEntity mapToEntity(StudentRegistrationRequest studentRegistrationRequest) {
        return EnrollmentEntity.builder()
                .username(studentRegistrationRequest.getUsername())
                .build();
    }


    public EnrollmentResponse mapToResponse(EnrollmentEntity enrollmentEntity) {
        return EnrollmentResponse.builder()
                .id(enrollmentEntity.getId())
                .name(enrollmentEntity.getUser().getName())
                .email(enrollmentEntity.getUser().getEmail())
                .phone(enrollmentEntity.getUser().getPhone())
                .username(enrollmentEntity.getUsername())
                .build();
    }

    public EnrollmentDetailsResponse mapToDetails(EnrollmentEntity enrollmentEntity) {
        return EnrollmentDetailsResponse.builder()
                .id(enrollmentEntity.getId())
                .name(enrollmentEntity.getUsername())
                .courses(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(enrollmentDetails->enrollmentDetails.getCourse().getName())
                        .toList())
                .certificates(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(EnrollmentDetailsEntity::getCertification)
                        .filter(Objects::nonNull)
                        .map(CertificationEntity::getTitle)
                        .toList())
                .completedActivities(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(courseEnrollment-> Map.entry(
                               courseEnrollment.getCourse().getName(),
                               courseEnrollment.getCompletedActivities())
                        )
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))
                )
                .progress(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(courseEnrollment->Map.entry(
                                courseEnrollment.getCourse().getName(),
                                courseEnrollment.getProgress()
                        ))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))

                )
                .completionDate(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(courseEnrollment->Map.entry(
                                courseEnrollment.getCourse().getName(),
                                courseEnrollment.getCompletionDate()
                        ))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))
                )
                .status(enrollmentEntity.getEnrollmentDetails().stream()
                        .map(courseEnrollment->Map.entry(
                                courseEnrollment.getCourse().getName(),
                                String.valueOf(courseEnrollment.getStatus())

                        ))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue
                        ))

                )
                .build();
    }
}

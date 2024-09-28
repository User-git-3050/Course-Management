package az.mscoursedictionary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Info {
    COURSE_WAS_CREATED("Course was created"),
    COURSE_WAS_DELETED("Course was deleted"),
    COURSE_WAS_MODIFIED("Course was modified"),
    STUDENT_REGISTERED("Student registered successfully"),
    INSTRUCTOR_REGISTERED("Instructor registered successfully"),
    ENROLLMENT_PROGRESS_MODIFIED("Enrollment progress modified successfully"),
    NOTIFICATION_SENT_SUCCESSFULLY("Notification sent successfully"),
    CERTIFICATIONS_ARE_GENERATED("Certifications are generated"),
    CERTIFICATE_OF_ACHIEVEMENT("Certificate of Achievement"),
    INSTRUCTOR_WAS_ADDED("Instructor was added to course");


    private final String message;
}

package az.mscoursedictionary.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessages {
    COURSE_NOT_FOUND("Course not found with given id : %s"),
    COURSE_NOT_FOUND_WITH_NAME("Course not found with given name : %s"),
    VALIDATION_FAILED("Validation failed"),
    USER_NOT_FOUND("User not found with username : %s"),
    METHOD_NOT_ALLOWED("Your request method is not allowed for this course id : %s"),
    ENROLLMENT_NOT_FOUND("Enrollment not found with given id : %s"),
    ENROLLMENT_NOT_FOUND_WITH_USERNAME("Enrollment not found with username : %s"),
    THERE_IS_NOT_ACTIVE_ENROLLMENT("There is no active enrollment in this course"),
    ENROLLMENT_ADDED_ONLY_ONCE("You can add enrollment only once"),
    ENROLLMENT_NOT_FOUND_WITH_COURSE("Enrollment doesn't have these course with id : %s"),
    CERTIFICATION_NOT_FOUND("Certificate not found with given id : %s"),
    NOTIFICATION_NOT_FOUND("Notification not found with given id : %s"),
    INSTRUCTOR_NOT_FOUND_WITH_USERNAME("Instructor not found with given username: %s");


    private final String message;
}

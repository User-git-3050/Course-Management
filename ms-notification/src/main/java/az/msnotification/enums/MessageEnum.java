package az.msnotification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageEnum {
    COMPLETED_COURSE_MESSAGE("Dear student, you have completed courses please check and get certificate"),
    PROGRESS_OF_COURSE_MESSAGE("Your progress in %s is %s % .Let's complete this course");

    private final String message;
}

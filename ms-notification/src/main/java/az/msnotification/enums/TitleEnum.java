package az.msnotification.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleEnum {
    COMPLETED_COURSES("Completed Courses"),
    COURSE_PROGRESSES("Course Progresses");

    private final String message;
}

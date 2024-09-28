package az.msnotification.service.impl;

import az.msnotification.client.CourseDictionaryClient;
import az.msnotification.dao.EnrollmentProgress;
import az.msnotification.dao.EnrollmentsWithCourse;
import az.msnotification.dao.NotificationRequest;
import az.msnotification.dao.NotificationResponse;
import az.msnotification.enums.MessageEnum;
import az.msnotification.enums.NotificationStatusEnum;
import az.msnotification.enums.TitleEnum;
import az.msnotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static az.msnotification.enums.MessageEnum.COMPLETED_COURSE_MESSAGE;
import static az.msnotification.enums.MessageEnum.PROGRESS_OF_COURSE_MESSAGE;
import static az.msnotification.enums.NotificationStatusEnum.*;
import static az.msnotification.enums.TitleEnum.COMPLETED_COURSES;
import static az.msnotification.enums.TitleEnum.COURSE_PROGRESSES;
import static java.lang.String.format;

@Service
public class NotificationServiceImpl implements NotificationService {


    private final CourseDictionaryClient courseDictionaryClient;

    @Autowired
    public NotificationServiceImpl(CourseDictionaryClient courseDictionaryClient) {
        this.courseDictionaryClient = courseDictionaryClient;
    }

    @Override
    public ResponseEntity<NotificationResponse> sendCustomNotification(NotificationRequest notificationRequest) {
        return courseDictionaryClient.sendCustomNotification(notificationRequest);
    }

    @Override
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return courseDictionaryClient.getAllNotifications();
    }

    @Override
    public ResponseEntity<NotificationResponse> getNotificationById(Long id) {
        return courseDictionaryClient.getNotificationById(id);
    }

    @Override
    public void triggerReminders() {
        List<EnrollmentsWithCourse> completedCoursesWithEnrollments = courseDictionaryClient.getCompletedCoursesWithEnrollments().getBody();
        assert completedCoursesWithEnrollments != null;
        completedCoursesNotification(completedCoursesWithEnrollments);


        List<EnrollmentProgress> progressOfEnrollments = courseDictionaryClient.checkEnrollmentProgress().getBody();
        assert progressOfEnrollments != null;
        progressOfEnrollmentNotification(progressOfEnrollments);



    }


    private void completedCoursesNotification(List<EnrollmentsWithCourse> completedCoursesWithEnrollments) {
        List<String> completedEnrollmentsName = new ArrayList<>();
        completedCoursesWithEnrollments.forEach((completedEnrollments) -> {
            completedEnrollmentsName.add(completedEnrollments.getEnrollmentName());
        });
        courseDictionaryClient.sendCustomNotification(
                NotificationRequest.builder()
                .title(COMPLETED_COURSES.getMessage())
                .message(COMPLETED_COURSE_MESSAGE.getMessage())
                .notificationDate(LocalDateTime.now())
                .notificationStatus(String.valueOf(INFO))
                .receivers(completedEnrollmentsName)
                .build());

    }

    private void progressOfEnrollmentNotification(List<EnrollmentProgress> progressOfEnrollments){
        progressOfEnrollments
                .forEach(enrollmentProgress -> courseDictionaryClient.sendCustomNotification(
                        NotificationRequest.builder()
                                .title(COURSE_PROGRESSES.getMessage())
                                .message(format(
                                        PROGRESS_OF_COURSE_MESSAGE.getMessage(),
                                        enrollmentProgress.getCourseName(),
                                        enrollmentProgress.getProgress()
                                ))
                                .notificationDate(LocalDateTime.now())
                                .notificationStatus(String.valueOf(INFO))
                                .receivers(Arrays.asList(enrollmentProgress.getEnrollmentName()))
                                .build())
                );

    }


}

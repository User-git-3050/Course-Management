package az.msnotification.client;

import az.msnotification.dao.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(
        name = "MS-COURSE-DICTIONARY"
)
public interface CourseDictionaryClient {
    @PostMapping("api/notification/dictionary")
    ResponseEntity<NotificationResponse> sendCustomNotification(@RequestBody NotificationRequest notificationRequest);


    @GetMapping("api/notification/dictionary")
    ResponseEntity<List<NotificationResponse>> getAllNotifications();


    @GetMapping("api/notification/dictionary/{notificationId}")
    ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long notificationId);

    @GetMapping("api/course/dictionary/completed")
    ResponseEntity<List<EnrollmentsWithCourse>> getCompletedCoursesWithEnrollments();

    @GetMapping("api/enrollments/dictionary/{enrollmentId}/name")
    ResponseEntity<String> getEnrollmentNameById(@PathVariable Long enrollmentId);

    @GetMapping("api/enrollments/dictionary/check/progress")
    ResponseEntity<List<EnrollmentProgress>> checkEnrollmentProgress();



}

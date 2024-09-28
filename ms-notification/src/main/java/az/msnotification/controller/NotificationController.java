package az.msnotification.controller;

import az.msnotification.dao.NotificationRequest;
import az.msnotification.dao.NotificationResponse;
import az.msnotification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/notifications/")
public class NotificationController {

    private final NotificationService notificationService;

    @Autowired
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping()
    public ResponseEntity<NotificationResponse> sendCustomNotification(@RequestBody NotificationRequest notificationRequest) {
        return notificationService.sendCustomNotification(notificationRequest);
    }

    @GetMapping()
    public ResponseEntity<List<NotificationResponse>> getAllNotifications(){
        return notificationService.getAllNotifications();
    }

    @GetMapping("{notificationId}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long notificationId){
        return notificationService.getNotificationById(notificationId);
    }

    @PostMapping("/reminders")
    public void triggerReminders(){
        notificationService.triggerReminders();
    }




}

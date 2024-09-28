package az.mscoursedictionary.controller;

import az.mscoursedictionary.dao.NotificationRequest;
import az.mscoursedictionary.dao.NotificationResponse;
import az.mscoursedictionary.enums.Info;
import az.mscoursedictionary.service.NotificationDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static az.mscoursedictionary.enums.Info.NOTIFICATION_SENT_SUCCESSFULLY;

@RestController
@RequestMapping("api/notification/dictionary")
public class NotificationController {
    private final NotificationDictionaryService notificationDictionaryService;

    @Autowired
    public NotificationController(NotificationDictionaryService notificationDictionaryService) {
        this.notificationDictionaryService = notificationDictionaryService;
    }

    @PostMapping()
    public ResponseEntity<NotificationResponse> sendCustomNotification(@RequestBody NotificationRequest notificationRequest) {
        return ResponseEntity.ok(notificationDictionaryService.sendCustomNotification(notificationRequest));
    }

    @GetMapping()
    public ResponseEntity<List<NotificationResponse>> getAllNotifications() {
        return ResponseEntity.ok(notificationDictionaryService.getAllNotifications());
    }

    @GetMapping("{notificationId}")
    public ResponseEntity<NotificationResponse> getNotificationById(@PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationDictionaryService.getNotificationById(notificationId));
    }




}

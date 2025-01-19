package com.example.PetOwner.controller;

import com.example.PetOwner.dtos.NotificationDTO;
import com.example.PetOwner.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Get all notifications
    @GetMapping("/getAllNotifications")
    public ResponseEntity<List<NotificationDTO>> getAllNotifications() {
        List<NotificationDTO> notifications = notificationService.getAllNotifications();
        return ResponseEntity.ok(notifications);
    }

    // Get notification by ID
    @GetMapping("/getNotificationById")
    public ResponseEntity<NotificationDTO> getNotificationById(@RequestParam("notificationId") Long notificationId) {
        NotificationDTO notificationDTO = notificationService.getNotificationById(notificationId);
        return ResponseEntity.ok(notificationDTO);
    }

    // Get notifications by user ID
    @GetMapping("/user")
    public ResponseEntity<List<NotificationDTO>> getNotificationsByUserId(@RequestParam("userId") Long userId) {
        List<NotificationDTO> notifications = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(notifications);
    }

    // Create a new notification
    @PostMapping("/createNotification")
    public ResponseEntity<NotificationDTO> createNotification(@RequestBody NotificationDTO notificationDTO) {
        NotificationDTO createdNotification = notificationService.createNotification(notificationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdNotification);
    }
}

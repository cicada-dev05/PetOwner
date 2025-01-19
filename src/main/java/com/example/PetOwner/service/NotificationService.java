package com.example.PetOwner.service;

import com.example.PetOwner.dtos.NotificationDTO;

import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getAllNotifications();

    NotificationDTO getNotificationById(Long notificationId);

    List<NotificationDTO> getNotificationsByUserId(Long userId);

    NotificationDTO createNotification(NotificationDTO notificationDTO);
}


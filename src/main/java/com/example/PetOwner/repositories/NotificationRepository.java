package com.example.PetOwner.repositories;

import com.example.PetOwner.model.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    Notification findByNotificationId(Long notificationId);

    List<Notification> findByUserId(Long userId);

    List<Notification> findByUserIdAndDeletedFlagFalse(Long userId);

    Notification findByNotificationIdAndDeletedFlagFalse(Long notificationId);
}

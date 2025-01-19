package com.example.PetOwner.serviceImp;

import com.example.PetOwner.dtos.NotificationDTO;
import com.example.PetOwner.model.Notification;
import com.example.PetOwner.repositories.NotificationRepository;
import com.example.PetOwner.service.NotificationService;
import com.example.PetOwner.utils.GeneralFunction;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ModelMapper modelMapper;

    private NotificationDTO convertToDTO(Notification notification) {
        return modelMapper.map(notification, NotificationDTO.class);
    }

    private Notification convertToEntity(NotificationDTO notificationDTO) {
        return modelMapper.map(notificationDTO, Notification.class);
    }

    @Override
    public List<NotificationDTO> getAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        return notifications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO getNotificationById(Long notificationId) {
        Notification notification = notificationRepository.findByNotificationIdAndDeletedFlagFalse(notificationId);
        if (notification == null) {
            throw new RuntimeException("Notification not found with ID: " + notificationId);
        }
        return convertToDTO(notification);
    }

    @Override
    public List<NotificationDTO> getNotificationsByUserId(Long userId) {
        List<Notification> notifications = notificationRepository.findByUserIdAndDeletedFlagFalse(userId);
        return notifications.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public NotificationDTO createNotification(NotificationDTO notificationDTO) {
        Notification notification = convertToEntity(notificationDTO);
        notification.setCreatedTs(LocalDateTime.now());
        notification.setNotificationId(GeneralFunction.generateId());
        Notification savedNotification = notificationRepository.save(notification);
        return convertToDTO(savedNotification);
    }
}


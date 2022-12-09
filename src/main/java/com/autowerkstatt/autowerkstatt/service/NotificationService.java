package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.NotificationRepository;
import com.autowerkstatt.autowerkstatt.dto.SubmitApplicationUserFaultsDto;
import com.autowerkstatt.autowerkstatt.entity.Car;
import com.autowerkstatt.autowerkstatt.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void save(Notification notification) {
        this.notificationRepository.save(notification);
    }

    public List<Notification> findAll() {
        return this.notificationRepository.findAll();
    }

    public List<Notification> getNotesUser() {
        return this.notificationRepository.getNotificationByStatus();
    }

    public List<Notification> getNotificationByUserId(Long userId) {
        return this.notificationRepository.findNotificationByUser(userId);
    }

    public Notification findById(Long id) {
        return this.notificationRepository.findById(id).orElse(null);
    }

    public List<Notification> getNewRequestUser() {
        return this.notificationRepository.getNotificationByStatusNew();
    }
}

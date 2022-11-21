package com.autowerkstatt.autowerkstatt.service;

import com.autowerkstatt.autowerkstatt.dao.NotificationRepository;
import com.autowerkstatt.autowerkstatt.entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

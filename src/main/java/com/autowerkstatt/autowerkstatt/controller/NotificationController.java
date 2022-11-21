package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Notification;
import com.autowerkstatt.autowerkstatt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/user-notification")
    public String userNotificationList(Model model) {
        List<Notification> notifications = notificationService.findAll();
        model.addAttribute("notifications", notifications);
        return "notificationUser";
    }
}

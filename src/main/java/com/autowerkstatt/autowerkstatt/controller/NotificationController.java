package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Notification;
import com.autowerkstatt.autowerkstatt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/mainPage-notification", method = RequestMethod.POST)
    public String notificationReturnMainPage() {
        return "mainPageUser";
    }
}

package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Notification;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.NotificationService;
import com.autowerkstatt.autowerkstatt.service.UsersDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UsersDetailsServiceImpl usersDetailsService;

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

    @GetMapping(value = "/get-modal-hodovka")
    public String modalHodovka() {
        return "modalHodovka";
    }

    @PostMapping(value = "/post-modal-hodovka")
    public String requestUserHodovka(@ModelAttribute(name = "notification") Notification notification) {
        Users users = usersDetailsService.findByEmailUser(notification.getUser().getEmail());
        notification.setUser(users);
        this.notificationService.save(notification);
        return "mainPageUser";
    }
}

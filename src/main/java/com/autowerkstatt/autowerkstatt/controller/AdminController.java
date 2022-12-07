package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.dto.AdminResponseToRequestDto;
import com.autowerkstatt.autowerkstatt.entity.Notification;
import com.autowerkstatt.autowerkstatt.enums.Status;
import com.autowerkstatt.autowerkstatt.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/admin-response-to-request")
    public String adminResponseToRequest(Model model) {
        List<Notification> notificationList = notificationService.findAll();
        model.addAttribute("notifications", notificationList);
        model.addAttribute("responseToRequest", new AdminResponseToRequestDto());
        return "adminResponseToRequest";
    }

    @PostMapping(value = "/adminResponseToRequest")
    public String adminResponse(@RequestBody AdminResponseToRequestDto adminResponseToRequestDto) {
        Notification notification = new Notification();
        notification.setDateFrom(adminResponseToRequestDto.getDateFrom());
        notification.setDateBefore(adminResponseToRequestDto.getDateBefore());
        notification.setPrice(adminResponseToRequestDto.getPrice());
        notification.setStatus(Status.PENDING);
        this.notificationService.save(notification);
        return "redirect:/admin-response-to-request";
    }

    @GetMapping(value = "/findOne")
    @ResponseBody
    public Optional<Notification> findOne(Long id) {
        return this.notificationService.findById(id);
    }
}

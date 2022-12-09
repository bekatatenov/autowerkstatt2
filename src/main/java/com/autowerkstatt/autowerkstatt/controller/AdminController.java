package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.dto.AdminResponseToRequestDto;
import com.autowerkstatt.autowerkstatt.entity.Notification;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.enums.Status;
import com.autowerkstatt.autowerkstatt.service.EmailSenderService;
import com.autowerkstatt.autowerkstatt.service.NotificationService;
import com.autowerkstatt.autowerkstatt.service.UsersDetailsServiceImpl;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Optional;

@Controller
public class AdminController {

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UsersDetailsServiceImpl usersDetailsService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping(value = "/admin-users-records")
    public String adminResponseToRequest(Model model) {
        model.addAttribute("notifications", notificationService.getNewRequestUser());
        model.addAttribute("responseToRequest", new AdminResponseToRequestDto());
        return "adminUsersRecords";
    }

    @RequestMapping(value = "/user-response", method = RequestMethod.GET)
    public ModelAndView userResponse(Long id) {
        ModelAndView modelAndView = new ModelAndView("responseUser");
        modelAndView.addObject("userResponse", notificationService.findById(id));
        return modelAndView;
    }

    @PostMapping(value = "/adminResponseToRequest")
    public String adminResponse(@ModelAttribute(name = "userResponse") AdminResponseToRequestDto adminResponseToRequestDto,
                                @RequestParam String email) {
        Notification notification = notificationService.findById(adminResponseToRequestDto.getId());
        Users users = usersDetailsService.findByEmailUser(email);

        emailSenderService.sendEmail(users.getEmail(), "Autowerkstatt", "Администратор ответил на вашу заявку, перейдите в свои записи." +
                "Если согласны то нажмите согласиться, иначе откажитесь");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
        try {
            notification.setDateFrom(simpleDateFormat.parse(adminResponseToRequestDto.getDateFrom()));
            notification.setDateBefore(simpleDateFormat.parse(adminResponseToRequestDto.getDateBefore()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        notification.setPrice(adminResponseToRequestDto.getPrice());
        notification.setStatus(Status.PENDING);
        this.notificationService.save(notification);
        return "redirect:/admin-users-records";
    }
}

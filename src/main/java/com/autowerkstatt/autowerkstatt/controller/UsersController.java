package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Token;
import com.autowerkstatt.autowerkstatt.enums.Roles;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.EmailSenderService;
import com.autowerkstatt.autowerkstatt.service.TokenService;
import com.autowerkstatt.autowerkstatt.service.UsersDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @Autowired
    private UsersDetailsServiceImpl usersDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmailSenderService emailSender;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage(@RequestParam(value = "error", required = false) String error,
                                  @RequestParam(value = "logout",	required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Почта или пароль неверны");
            model.setViewName("/login");
        }
        if (logout != null) {
            model.addObject("logout", "Logged out successfully.");
            model.setViewName("/login");
        }
        return model;
    }

    @RequestMapping(value = "/mainPageUser", method = RequestMethod.GET)
    public String mainPageUser() {
        return "mainPageUser";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @PostMapping(value = "/registartion")
    public String registration(@ModelAttribute(name = "user") Users user) {
        user.setRole(Roles.USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.usersDetailsService.save(user);
        return "registration";
    }

    @GetMapping(value = "/forgotPassword")
    public String resetPasswordPage() {
        return "forgotPasswordPage";
    }

    @PostMapping(value = "/passwordRecoveryEmail")
    public String getEmailForResetPassword(@RequestParam String email, Model model) {
        Users saved = usersDetailsService.findByEmailUser(email);
        Token token = tokenService.saveToken(saved, tokenService.makeToken());

        emailSender.sendEmail(saved.getEmail(), "Восстановление пароля", String.valueOf(token.getToken()));
        return "newPassword";
    }

    @GetMapping(value = "/newPassword")
    public String newPasswordPage() {
        return "newPassword";
    }

    @PostMapping(value = "/newPasswordUser")
    public String newPassword(@RequestParam(name = "userEmail", required = false) String userEmail,
                              @RequestParam(name = "token", required = false) String token,
                              @RequestParam(name = "newPassword", required = false) String newPassword) {
        Users users = usersDetailsService.findByEmailUser(userEmail);
        Token byUserAndToken = tokenService.findByUserAndToken(users, Integer.parseInt(token));

        users.setPassword(bCryptPasswordEncoder.encode(newPassword));
        usersDetailsService.save(users);

        tokenService.deleteToken(byUserAndToken);

        return "login";
    }
}

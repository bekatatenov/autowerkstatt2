package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.Enums.Roles;
import com.autowerkstatt.autowerkstatt.entity.Token;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.TokenService;
import com.autowerkstatt.autowerkstatt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private TokenService tokenService;

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

    @GetMapping(value = "/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("registration");
        modelAndView.addObject("user", new Users());
        return modelAndView;
    }

    @PostMapping(value = "/registartion")
    public String registration(@ModelAttribute(name = "user") Users user) {
        user.setRole(Roles.USER);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.usersService.save(user);
        return "login";
    }

    @GetMapping("/resetPassword")
    public String resetPasswordPage(){
        return "resetPasswordPage";
    }

    @PostMapping("/resetPasswrod")
    public String getEmailForResetPswrd(@RequestParam String email, Model model){
        Users saved = usersService.findByEmail(email);
        Token token = tokenService.saveToken(saved, tokenService.makeToken());

        // mailSernder.sendToken(user, token.getToken);
        return "newPassword";
    }

    @GetMapping("/newPassword")
    public String newPasswordPage(){
        return "newPassword";
    }
    @PostMapping("/newPassword")
    public String newPassword(@RequestParam String userEmail, @RequestParam String token, @RequestParam String newPassword){
        Users user = usersService.findByEmail(userEmail);
        Token byUserAndToken = tokenService.findByUserAndToken(user, Integer.parseInt(token));

        user.setPassword(bCryptPasswordEncoder.encode(newPassword));
        usersService.save(user);

        tokenService.deleteToken(byUserAndToken);

        return "login";
    }
}

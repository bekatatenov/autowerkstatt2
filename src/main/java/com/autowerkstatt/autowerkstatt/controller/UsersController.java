package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.Enums.Roles;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.RoleService;
import com.autowerkstatt.autowerkstatt.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsersController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UsersService usersService;

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

    @PostMapping(value = "/registartion-user")
    public String registration(@ModelAttribute(name = "user") Users user) {
        user.setRoles(this.roleService.getRoleByName(Roles.USER.name()));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        this.usersService.save(user);
        return "registration";
    }
}

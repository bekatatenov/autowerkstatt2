package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Turn;
import com.autowerkstatt.autowerkstatt.service.TurnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TurnController {

    @Autowired
    private TurnService turnService;

    @GetMapping(value = "/turn-hodovka")
    public String turnHodovka() {
        return "";
    }
}

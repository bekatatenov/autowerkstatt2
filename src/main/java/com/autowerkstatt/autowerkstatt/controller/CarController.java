package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Car;
import com.autowerkstatt.autowerkstatt.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/create-car")
    public String create(Model model) {
        model.addAttribute("car", new Car());
        return "SaveCar";
    }

    @PostMapping(value = "/car-save")
    public String carSave(@ModelAttribute(name = "car") Car car) {
        carService.carSave(car);
        return "mainPageUser";
    }
}

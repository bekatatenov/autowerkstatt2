package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.dto.CarDto;
import com.autowerkstatt.autowerkstatt.entity.Car;
import com.autowerkstatt.autowerkstatt.entity.Models;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.CarService;
import com.autowerkstatt.autowerkstatt.service.MarkService;
import com.autowerkstatt.autowerkstatt.service.UsersDetailsServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CarController {

    @Autowired
    private CarService carService;

    @Autowired
    private MarkService markService;

    @Autowired
    private UsersDetailsServiceImpl usersDetailsService;

    @GetMapping(value = "/saveCarOpen")
    public String mainPage(Model model) {
        model.addAttribute("marksList", markService.getAllMarks());
        model.addAttribute("car", new Car());
        return "saveCar";
    }

    @GetMapping(value = "/getModelsCar")
    public @ResponseBody String getModels(@RequestParam Long marksId) {
        String json = null;
        List<Object[]> list = markService.getModelByMark(marksId);
        try {
            json = new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException exception) {
            exception.printStackTrace();
        }
        return json;
    }

    @PostMapping(value = "/addCarUser")
    public String addCar(@ModelAttribute(name = "car") CarDto carDto) {
        Users users = usersDetailsService.findByEmailUser(carDto.getUserEmail());
        Models models = carService.getByModel(carDto.getModelName()).getModels();

        Car car = new Car();
        car.setUser(users);
        car.setModels(models);
        this.carService.addCar(car);
        return "mainPageUser";
    }
}


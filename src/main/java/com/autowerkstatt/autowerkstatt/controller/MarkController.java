//package com.autowerkstatt.autowerkstatt.controller;
//
//import com.autowerkstatt.autowerkstatt.entity.Car;
//import com.autowerkstatt.autowerkstatt.entity.Models;
//import com.autowerkstatt.autowerkstatt.service.CarService;
//import com.autowerkstatt.autowerkstatt.service.MarkService;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//public class MarkController {
//
//    @Autowired
//    private MarkService markService;
//
//    @Autowired
//    private CarService carService;
//
//    @GetMapping(value = "/")
//    public String mainPage(Model model) {
//        model.addAttribute("marksList", markService.getAllMarks());
//        model.addAttribute("car", new Car());
//        return "mainPage";
//    }
//
//    @GetMapping(value = "/getModels")
//    public @ResponseBody String getModels(@RequestParam Long marksId) {
//        String json = null;
//        List<Object[]> list = markService.getModelByMark(marksId);
//        try {
//            json = new ObjectMapper().writeValueAsString(list);
//        } catch (JsonProcessingException exception) {
//            exception.printStackTrace();
//        }
//        return json;
//    }
//}

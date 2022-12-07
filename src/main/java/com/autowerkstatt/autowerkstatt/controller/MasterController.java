package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.dao.MasterRepository;
import com.autowerkstatt.autowerkstatt.entity.Master;
import com.autowerkstatt.autowerkstatt.enums.Faults;
import com.autowerkstatt.autowerkstatt.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.NoSuchElementException;


@Controller
public class MasterController {

    @Autowired
    private MasterService masterService;

    @Autowired
    private MasterRepository masterRepository;

    @GetMapping(value = "/create-master")
    public String create(Model model) {
        model.addAttribute("master", new Master());
        return "master";
    }

    @PostMapping(value = "/master-save")
    public String save(@ModelAttribute(name =  "master") Master master) {
        masterService.save(master);
        return "hello";
    }

    @PostMapping(value = "/get-all-masters")
    public ModelAndView getAllMasters(){
        ModelAndView modelAndView = new ModelAndView("AllMasters");
        List<Master> allMasters = masterService.allMasters();
        modelAndView.addObject("AllMasters", allMasters);
    return modelAndView;
    }

    @DeleteMapping(value = "/master-delete")
    public String delete(@RequestParam(name = "id") Long id) {
        try {
            this.masterService.delete(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "FAILED";
        }
        return "SUCCESS";
    }

    @PostMapping( "/master-update")
    public ResponseEntity<Master> updateMaster(@RequestParam Long id, @RequestParam String firstName,
                                               @RequestParam String lastName, @RequestParam Faults faults) {
        try {
            Master master = masterRepository.findById(id)
                    .orElseThrow(()->new NoSuchElementException("Мастер не найден"));
            master.setFirstName(firstName);
            master.setLastName(lastName);
            master.setFaults(faults);
            return new ResponseEntity<Master>(masterRepository.save(master), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

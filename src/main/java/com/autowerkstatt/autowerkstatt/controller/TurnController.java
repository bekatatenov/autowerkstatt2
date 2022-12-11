package com.autowerkstatt.autowerkstatt.controller;

import com.autowerkstatt.autowerkstatt.entity.Turn;
import com.autowerkstatt.autowerkstatt.entity.Users;
import com.autowerkstatt.autowerkstatt.service.TurnService;
import com.autowerkstatt.autowerkstatt.service.UsersDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TurnController {

    @Autowired
    private TurnService turnService;

    @Autowired
    private UsersDetailsServiceImpl usersDetailsService;

    @GetMapping(value = "/turn-hodovka")
    public String turnHodovka(Model model) {
        List<Turn> turnList = turnService.findTurnByHodovkaAndStatus();
        model.addAttribute("turnHodovka", turnList);
        return "turnHodovka";
    }

    @GetMapping(value = "/turn-electrician")
    public String turnElectrician(Model model) {
        List<Turn> turnList = turnService.findTurnByElectricianAndStatus();
        model.addAttribute("turnElectrician", turnList);
        return "turnElectrician";
    }

    @GetMapping(value = "/turn-DVS")
    public String turnDVS(Model model) {
        List<Turn> turnList = turnService.findTurnByDVSAndStatus();
        model.addAttribute("turnDVS", turnList);
        return "turnDVS";
    }

    @GetMapping(value = "/turn-more")
    public String turnMore(Model model) {
        List<Turn> turnList = turnService.findTurnByMoreAndStatus();
        model.addAttribute("turnMore", turnList);
        return "turnMore";
    }

    @GetMapping(value = "/turn-works")
    public String turnWorks(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Users users = usersDetailsService.findByEmailUser(auth.getName());

        List<Turn> turnListWorks = turnService.findTurnByStatusAndUser(users.getId());
        model.addAttribute("carTurnWork", turnListWorks);
        return "turnWorks";
    }

    @RequestMapping(value = "/mainPage-turn", method = RequestMethod.POST)
    public String returnMainPageAdmin() {
        return "mainPageAdmin";
    }

    @RequestMapping(value = "/mainPage-user", method = RequestMethod.POST)
    public String returnMainPageUser() {
        return "mainPageUser";
    }
}

package com.binu.proj1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {

    @RequestMapping(value = "/home")
    public ModelAndView getDateAndTime() {
        System.out.println("home");
        return new ModelAndView("home");
    }

    @GetMapping("/")
    public String homePage(Model model) {
        //model.addAttribute("appName", appName);
        return "home";
    }
}

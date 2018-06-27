package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @GetMapping("/")
    public String index() {
        return "views/index";
    }

    @GetMapping("/greeting")
    public ModelAndView greeting(Model model) {
        ModelAndView modelAndView = new ModelAndView("views/greeting");
        modelAndView.addObject("name", "world");
        return modelAndView;
    }

}
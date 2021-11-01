package com.example.LaBeaute.controllers;

import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(String name, Model model) {
        model.addAttribute("title", "Home page");
        return "home";
    }

}
package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Days;
import com.example.LaBeaute.repo.DaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


    @Autowired
    private DaysRepository daysRepository;

    @GetMapping("/admin")
    public String doAdminPage(Model model) {
        model.addAttribute("title","Admin Page");
        Iterable<Days> days = daysRepository.findAll();
        model.addAttribute("days",days);
        return "adminPage";
    }
}

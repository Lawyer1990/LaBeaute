package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Customers;
import com.example.LaBeaute.models.Days;
import com.example.LaBeaute.models.Posts;
import com.example.LaBeaute.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

@Controller
public class AdminController {


    @Autowired
    private DaysRepository daysRepository;

    @Autowired
    private CustomersRepository customersRepository;

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private StatusesRepository statusesRepository;

    @Autowired
    private StuffRepository stuffRepository;


    @GetMapping("/admin/menu")
    public String getMainMenuAdminPage(){
        return "admin/adminMenuPage";
    }



    @GetMapping("/admin")
    public String doAdminPage(Model model) {
        Calendar calendar = new GregorianCalendar();
        model.addAttribute("dayToday", calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_YEAR));
        model.addAttribute("title", "Admin Page");
        Iterable<Days> days = daysRepository.findAll();
        model.addAttribute("days", days);
        model.addAttribute("titleDays", "Days information");

        return "adminPage";
    }

    @PostMapping("/admin/add")
    public String postAdminPage(@RequestParam String data, Model model) {
        Days days = new Days(data);
        daysRepository.save(days);
        return "redirect:/admin";
    }






}

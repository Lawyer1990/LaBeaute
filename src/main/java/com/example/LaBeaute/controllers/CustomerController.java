package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Customers;
import com.example.LaBeaute.models.Days;
import com.example.LaBeaute.repo.CustomersRepository;
import com.example.LaBeaute.repo.DaysRepository;
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
public class CustomerController {
    @Autowired
    private CustomersRepository customersRepository;
    private int count=1;



    @GetMapping("/customers/{id}")
    public String doCustomersPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title","Customers Page");
        Optional<Customers> customers = customersRepository.findById(id);
        ArrayList<Customers> arrayList = new ArrayList<>();
        customers.ifPresent(arrayList::add);
        model.addAttribute("customersId",arrayList);
        return "customersPage";
    }

}

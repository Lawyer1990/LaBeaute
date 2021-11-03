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
    private int count = 1;

    @GetMapping("/customers")
    public String showCustomers(Model model) {
        Iterable<Customers> customers = customersRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("titleCustomers", "Customers information");
        return "customers/customersPage";
    }

    @GetMapping("/customers/{id}")
    public String doCustomersPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Customers Page");
        Optional<Customers> customers = customersRepository.findById(id);
        ArrayList<Customers> arrayList = new ArrayList<>();
        customers.ifPresent(arrayList::add);
        model.addAttribute("customersId", arrayList);
        return "customersPage";
    }

    @PostMapping("/customers/add")
    public String addCustomerAdminPage(@RequestParam String newCustomerName, String newCustomerEmail, String newCustomerPassword, String newCustomerNumber, Model model) {
        Customers customers = new Customers(newCustomerName, newCustomerEmail, newCustomerPassword, newCustomerNumber);
        customersRepository.save(customers);
        return "redirect:/customers";
    }


    @GetMapping("/customers/{id}/edit")
    public String doAdminEditCustomerPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Edit Customer Page");
        Optional<Customers> customers = customersRepository.findById(id);
        ArrayList<Customers> arrayList = new ArrayList<>();
        customers.ifPresent(arrayList::add);
        model.addAttribute("customersId", arrayList);
        return "customers/updateCustomersPage";
    }

    @PostMapping("/customers/{id}/edit")
    public String updateCustomerAdminPage(@PathVariable(value = "id") long id, @RequestParam String newCustomerName, String newCustomerEmail, String newCustomerPassword, String newCustomerNumber, Model model) {
        Customers customers = customersRepository.findById(id).orElseThrow();
        customers.setEmail(newCustomerEmail);
        customers.setName(newCustomerName);
        customers.setPassword(newCustomerPassword);
        customers.setNumber(newCustomerNumber);
        customersRepository.save(customers);
        return "redirect:/customers";
    }

    @PostMapping("/customers/{id}/remove")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id, Model model) {
        Customers customers = customersRepository.findById(id).orElseThrow();
        customersRepository.delete(customers);
        return "redirect:/customers";
    }
}

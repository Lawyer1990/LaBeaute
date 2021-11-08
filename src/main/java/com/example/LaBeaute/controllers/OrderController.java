package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.*;
import com.example.LaBeaute.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private StatusesRepository statusesRepository;

    @Autowired
    private StuffRepository stuffRepository;

    @Autowired
    private ServicesRepository servicesRepository;

    @Autowired
    private CustomersRepository customersRepository;


    @GetMapping
    public String showOrderPage(Model model){
        Iterable<Order> order = orderRepository.findAll();
        Iterable<Statuses> status = statusesRepository.findAll();
        Iterable<Stuff> stuff = stuffRepository.findAll();
        Iterable<Services> service = servicesRepository.findAll();
        Iterable<Customers> customer = customersRepository.findAll();
        model.addAttribute("order",order);
        model.addAttribute("status",status);
        model.addAttribute("stuff",stuff);
        model.addAttribute("service",service);
        model.addAttribute("customer",customer);
        model.addAttribute("title","Информация о заказанных услугах");
        return "/order/orderPage";
    }
}

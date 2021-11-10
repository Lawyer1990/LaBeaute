package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.*;
import com.example.LaBeaute.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

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
    public String showOrderPage(Model model) {
        Iterable<Order> order = orderRepository.findAll();
        Iterable<Statuses> status = statusesRepository.findAll();
        Iterable<Stuff> stuff = stuffRepository.findAll();
        Iterable<Services> service = servicesRepository.findAll();
        Iterable<Customers> customer = customersRepository.findAll();
        model.addAttribute("order", order);
        model.addAttribute("status", status);
        model.addAttribute("stuff", stuff);
        model.addAttribute("service", service);
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Информация о заказанных услугах");
        return "order/orderPage";
    }

    @PostMapping("add")
    public String addOrderPage(
            @RequestParam
                    String data, String time1, String time2, long status, long nameStuff,
                                                             long service, long customer) {
        Order order = new Order(data, time1, time2);
        Statuses status_bd = new Statuses();
        Stuff stuff = new Stuff();
        Services services = new Services();
        Customers customers = new Customers();
        status_bd.setId(status);
        stuff.setId(nameStuff);
        services.setId(service);
        customers.setId(customer);
        order.setStatus(status_bd);
        order.setStuff_name(stuff);
        order.setService(services);
        order.setCustomers_name(customers);
        orderRepository.save(order);
        return "redirect:/order";
    }

    @GetMapping("{id}/edit")
    public String doAdminEditCustomerPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Редактировать информацию о заказах");
        Optional<Order> order = orderRepository.findById(id);
        ArrayList<Order> arrayList = new ArrayList<>();
        order.ifPresent(arrayList::add);
        Iterable<Statuses> status = statusesRepository.findAll();
        Iterable<Stuff> stuff = stuffRepository.findAll();
        Iterable<Services> service = servicesRepository.findAll();
        Iterable<Customers> customer = customersRepository.findAll();
        model.addAttribute("orderId", arrayList);
        model.addAttribute("status", status);
        model.addAttribute("stuff", stuff);
        model.addAttribute("service", service);
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Информация о заказанных услугах");
        return "order/updateOrderPage";
    }

    @PostMapping("{id}/edit")
    public String updateOrderPage(@PathVariable(value = "id") long id, @RequestParam
            String data, String time1, String time2, long status, long nameStuff,
                                  long service, long customer) {
        Order order = orderRepository.findById(id).orElseThrow();
        Statuses status_db = new Statuses();
        Stuff stuff_db = new Stuff();
        Services service_db = new Services();
        Customers customer_db = new Customers();
        status_db.setId(status);
        stuff_db.setId(nameStuff);
        service_db.setId(service);
        customer_db.setId(customer);
        order.setData(data);
        order.setTime1(time1);
        order.setTime2(time2);
        order.setStatus(status_db);
        order.setStuff_name(stuff_db);
        order.setService(service_db);
        order.setCustomers_name(customer_db);
        orderRepository.save(order);
        return "redirect:/order";
    }
    @PostMapping("{id}/remove")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/order";
    }
}

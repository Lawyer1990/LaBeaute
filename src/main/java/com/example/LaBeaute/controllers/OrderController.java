package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.*;
import com.example.LaBeaute.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Optional;

import static com.example.LaBeaute.constant.Constant.ErrorMessages.*;
import static com.example.LaBeaute.constant.Constant.Titles.ORDER;

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

    private boolean status = true;
    private ArrayList arrayList1 = new ArrayList();
    Calendar calendar = new GregorianCalendar();
    private void cleanFields(){
        status=true;
    }

    @GetMapping
    public String showOrderPage(Model model) {
        model.addAttribute("title", ORDER);
        if (status == true) {
            model.addAttribute("titleOnPage", ORDER);
            model.addAttribute("color", "black");
        } else {
            model.addAttribute("titleOnPage", "Ошибка:");
            model.addAttribute("color", "red");
            model.addAttribute("arrayList", arrayList1);
        }
        model.addAttribute("dayToday", calendar.get(Calendar.YEAR) + "-" + Integer.toString(calendar.get(Calendar.MONTH)+1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
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
        cleanFields();
        return "order/orderPage";
    }


    @PostMapping("add")
    public String addOrderPage(
            @RequestParam
                    String data, String time1, String time2, long status, long nameStuff,
            long service, long customer) {
        int time1Eq,time2Eq,dataEq;
        String dataToday = Integer.toString(calendar.get(Calendar.YEAR)) + Integer.toString(calendar.get(Calendar.MONTH)+1)+Integer.toString(calendar.get(Calendar.DAY_OF_MONTH)) ;
        dataEq=Integer.parseInt(data.replaceAll("-",""));
        time1Eq=Integer.parseInt(time1.replaceAll(":",""));
        time2Eq=Integer.parseInt(time2.replaceAll(":",""));
        int i=0;
        arrayList1.clear();
        if (data.equals("") || time1.equals("") || time2.equals("")){
            i++;
            arrayList1.add(OBLIGATION_FIELD);
        }
        if (status == 1 && customer == 0){
            i++;
            arrayList1.add(WORK);
        }
        if (((status == 2)||(status == 3)) && (customer != 0)) {
            i++;
            arrayList1.add(NOTWORK);
        }
        if (time2.equals(time1)) {
            i++;
            arrayList1.add(TIME_EQUALS);
        }
        if (time1Eq>time2Eq) {
            i++;
            arrayList1.add(TIME_BEFORE);
        }
        if (dataEq>Integer.parseInt(dataToday)){
            i++;
            arrayList1.add(DATA_BEFORE);
        }
            if (i==0){
                this.status = true;
                Order order = new Order(data, time1, time2);
                Statuses status_bd = new Statuses();
                Stuff stuff = new Stuff();
                Services services = new Services();
                Customers customers = new Customers();
                status_bd.setId(status);
                stuff.setId(nameStuff);
                services.setId(service);
                customers.setId(customer);
                order.setCustomers_name(customers);
                order.setStatus(status_bd);
                order.setStuff_name(stuff);
                order.setService(services);
                orderRepository.save(order);
            }else this.status = false;

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

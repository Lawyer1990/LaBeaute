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

    private static boolean status = true;
    private static ArrayList arrayList1 = new ArrayList();
    Calendar calendar = new GregorianCalendar();

    private void cleanFields() {
        status = true;
    }

    private void setAttributeError(Model model) {
        model.addAttribute("titleOnPage", "Ошибка:");
        model.addAttribute("color", "red");
        model.addAttribute("arrayList", arrayList1);
    }

    private void setAttributeErrorTrue(Model model) {
        model.addAttribute("titleOnPage", ORDER);
        model.addAttribute("color", "black");
    }

    @GetMapping
    public String showOrderPage(Model model) {
        model.addAttribute("title", ORDER);
        if (status == true) {
            setAttributeErrorTrue(model);
        } else {
            setAttributeError(model);
        }
        model.addAttribute("dayToday", calendar.get(Calendar.YEAR) + "-" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
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

    public int errorMessage(String data, String time1, String time2, long status, long customer) {
        int time1Eq, time2Eq, dataEq;
        String dataToday = Integer.toString(calendar.get(Calendar.YEAR)) + Integer.toString(calendar.get(Calendar.MONTH) + 1) + Integer.toString(calendar.get(Calendar.DAY_OF_MONTH));
        dataEq = Integer.parseInt(data.replaceAll("-", ""));
        if (!time1.equals("")){
            time1Eq = Integer.parseInt(time1.replaceAll(":", ""));
        } else time1Eq=0;
        if (!time2.equals("")) {
            time2Eq = Integer.parseInt(time2.replaceAll(":", ""));
        } else time2Eq=1;
        int i = 0;
        arrayList1.clear();
        if (data.equals("") || time1.equals("") || time2.equals("")) {
            i++;
            arrayList1.add(OBLIGATION_FIELD);
        }
        if (status == 1 && customer == 0) {
            i++;
            arrayList1.add(WORK);
        }
        if (((status == 2) || (status == 3)) && (customer != 0)) {
            i++;
            arrayList1.add(NOTWORK);
        }
        if (time2.equals(time1)&&(!time1.equals(""))&&(!time2.equals(""))){
            i++;
            arrayList1.add(TIME_EQUALS);
        }
        if (time1Eq > time2Eq) {
            i++;
            arrayList1.add(TIME_BEFORE);
        }
        if (dataEq > Integer.parseInt(dataToday)) {
            i++;
            arrayList1.add(DATA_BEFORE);
        }
        return i;
    }

    @PostMapping("add")
    public String addOrderPage(
            @RequestParam
                    String data, String time1, String time2, long status, long nameStuff,
            long service, long customer) {
        if (errorMessage(data, time1, time2, status, customer) == 0) {
            setStatus(true);
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

        } else setStatus(false);

        return "redirect:/order";
    }

    @GetMapping("{id}/edit")
    public String showUpdateOrderPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("titleOnPage", "Редактировать информацию о заказах");
        model.addAttribute("dayToday", calendar.get(Calendar.YEAR) + "-" + Integer.toString(calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
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

    public void clearArrayList1() {
        arrayList1.clear();
    }

    public static void setStatus(Boolean statuses) {
        status = statuses;
    }


    @PostMapping("{id}/edit")
    public String updateOrderPage(@PathVariable(value = "id") long id, @RequestParam
            String data, String time1, String time2, long status, long nameStuff,
                                  long service, long customer, Model model) {
        if (errorMessage(data, time1, time2, status, customer) == 0) {
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
            setStatus(true);
            setAttributeErrorTrue(model);
            orderRepository.save(order);
            return "redirect:/order";
        } else {
            setStatus(false);
            showUpdateOrderPage(id, model);
            setAttributeError(model);
            return "order/updateOrderPage";
        }

    }

    @PostMapping("{id}/remove")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id) {
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/order";
    }
}

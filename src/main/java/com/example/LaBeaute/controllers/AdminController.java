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


    @GetMapping("/admin")
    public String doAdminPage(Model model) {
        Calendar calendar = new GregorianCalendar();
        model.addAttribute("dayToday", calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.DAY_OF_YEAR));
        model.addAttribute("title", "Admin Page");
        Iterable<Days> days = daysRepository.findAll();
        model.addAttribute("days", days);
        model.addAttribute("titleDays", "Days information");
        Iterable<Customers> customers = customersRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("titleCustomers", "Customers information");
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("titlePosts", "Posts information");
        return "adminPage";
    }

    @PostMapping("/admin/add")
    public String postAdminPage(@RequestParam String data, Model model) {
        Days days = new Days(data);
        daysRepository.save(days);
        return "redirect:/admin";
    }

    @PostMapping("/admin/addCustomer")
    public String addCustomerAdminPage(@RequestParam String newCustomerName, String newCustomerEmail, String newCustomerPassword, String newCustomerNumber, Model model) {
        Customers customers = new Customers(newCustomerName, newCustomerEmail, newCustomerPassword, newCustomerNumber);
        customersRepository.save(customers);
        return "redirect:/admin";
    }

    @PostMapping("/admin/addPost")
    public String addPostAdminPage(@RequestParam String newPost, Model model) {
        Posts posts = new Posts(newPost);
        postsRepository.save(posts);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/editCustomer")
    public String doAdminEditCustomerPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Edit Customer Page");
        Optional<Customers> customers = customersRepository.findById(id);
        ArrayList<Customers> arrayList = new ArrayList<>();
        customers.ifPresent(arrayList::add);
        model.addAttribute("customersId", arrayList);
        return "adminEditCustomerPage";
    }

    @PostMapping("/admin/{id}/editCustomer")
    public String updateCustomerAdminPage(@PathVariable(value = "id") long id, @RequestParam String newCustomerName, String newCustomerEmail, String newCustomerPassword, String newCustomerNumber, Model model) {
        Customers customers = customersRepository.findById(id).orElseThrow();
        customers.setEmail(newCustomerEmail);
        customers.setName(newCustomerName);
        customers.setPassword(newCustomerPassword);
        customers.setNumber(newCustomerNumber);
        customersRepository.save(customers);
        return "redirect:/admin";
    }

    @PostMapping("/admin/{id}/removeCustomer")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id, Model model) {
        Customers customers = customersRepository.findById(id).orElseThrow();
        customersRepository.delete(customers);
        return "redirect:/admin";
    }

    @GetMapping("/admin/{id}/editPosts")
    public String doAdminEditPostPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Update Post Page");
        Optional<Posts> post = postsRepository.findById(id);
        ArrayList<Posts> arrayList = new ArrayList<>();
        post.ifPresent(arrayList::add);
        model.addAttribute("postId", arrayList);
        return "adminEditPostPage";
    }

    @PostMapping("/admin/{id}/editPosts")
    public String updatePostAdminPage(@PathVariable(value = "id") long id, @RequestParam String newPost, Model model) {
        Posts posts = postsRepository.findById(id).orElseThrow();
        posts.setPost(newPost);
        postsRepository.save(posts);
        return "redirect:/admin";
    }


    @PostMapping("/admin/{id}/removePosts")
    public String removePostAdminPage(@PathVariable(value = "id") long id, Model model) {
        Posts posts = postsRepository.findById(id).orElseThrow();
        postsRepository.delete(posts);
        return "redirect:/admin";
    }
}

package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Posts;
import com.example.LaBeaute.models.Stuff;
import com.example.LaBeaute.repo.PostsRepository;
import com.example.LaBeaute.repo.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class StuffController {
    @Autowired
    private StuffRepository stuffRepository;


    @Autowired
    private PostsRepository postsRepository;


    @GetMapping("/stuff")
    public String showCustomers(Model model) {
        Stuff stuff1 = new Stuff();
        Iterable<Stuff> stuff = stuffRepository.findAll();
        Iterable<Posts> posts = postsRepository.findAll();

        model.addAttribute("stuff", stuff);
        model.addAttribute("post", posts);
        model.addAttribute("titleStuff", "Информация о работниках");
        return "stuff/stuffPage";
    }


    @PostMapping("/stuff/add")
    public String addCustomerAdminPage(@RequestParam String newStuffName, String
            newStuffEmail, String newStuffPassword, String newStuffNumber, int newStuffPost, Model model) {
        Stuff stuff = new Stuff(newStuffName, newStuffEmail, newStuffPassword, newStuffNumber, newStuffPost);
        stuffRepository.save(stuff);
        return "redirect:/stuff";
    }


    @GetMapping("/stuff/{id}/edit")
    public String doAdminEditCustomerPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Редактировать информацию о работниках");
        Optional<Stuff> stuff = stuffRepository.findById(id);
        ArrayList<Stuff> arrayList = new ArrayList<>();
        stuff.ifPresent(arrayList::add);
        model.addAttribute("stuffId", arrayList);
        return "stuff/updateStuffPage";
    }

    @PostMapping("/stuff/{id}/edit")
    public String updateCustomerAdminPage(@PathVariable(value = "id") long id, @RequestParam String newCustomerName,
                                          String newCustomerEmail, String newCustomerPassword,
                                          String newCustomerNumber, int newStuffPost, Model model) {
        Stuff stuff = stuffRepository.findById(id).orElseThrow();
        stuff.setName(newCustomerName);
        stuff.setEmail(newCustomerEmail);
        stuff.setPassword(newCustomerPassword);
        stuff.setNumber(newCustomerNumber);
        stuff.setPost_id(newStuffPost);
        stuffRepository.save(stuff);
        return "redirect:/stuff";
    }

    @PostMapping("/stuff/{id}/remove")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id, Model model) {
        Stuff stuff = stuffRepository.findById(id).orElseThrow();
        stuffRepository.delete(stuff);
        return "redirect:/stuff";
    }
}

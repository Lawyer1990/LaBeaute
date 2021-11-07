package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Posts;
import com.example.LaBeaute.models.Stuff;
import com.example.LaBeaute.repo.PostsRepository;
import com.example.LaBeaute.repo.StuffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("stuff")
public class StuffController {
    @Autowired
    private StuffRepository stuffRepository;


    @Autowired
    private PostsRepository postsRepository;


    @GetMapping("{id}")
    public String changeId(@PathVariable(value = "id") long id, Model model){
        Stuff stuff = stuffRepository.findById(id).orElseThrow();
        Posts posts = new Posts();
        posts.addStuffPost(stuff);
        return "stuff/stuffPage";
    }

    @GetMapping
    public String showCustomers(Model model) {
        Iterable<Stuff> stuff = stuffRepository.findAll();
        Iterable<Posts> posts = postsRepository.findAll();
        model.addAttribute("stuff", stuff);
        model.addAttribute("post", posts);
        model.addAttribute("titleStuff", "Информация о работниках");
        return "stuff/stuffPage";
    }


    @PostMapping("add")
    public String addCustomerAdminPage(
            @RequestParam String newStuffName, String newStuffEmail,
                          String newStuffPassword, String newStuffNumber,
                          long newStuffPost, Model model) {
        Stuff stuff = new Stuff(newStuffName, newStuffEmail, newStuffPassword, newStuffNumber);
        Posts posts = new Posts();
        posts.setId(newStuffPost);
        stuff.setPost_name(posts);
        stuffRepository.save(stuff);
        return "redirect:/stuff";
    }


    @GetMapping("{id}/edit")
    public String doAdminEditCustomerPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Редактировать информацию о работниках");
        Optional<Stuff> stuff = stuffRepository.findById(id);
        ArrayList<Stuff> arrayList = new ArrayList<>();
        stuff.ifPresent(arrayList::add);
        model.addAttribute("stuffId", arrayList);
        return "stuff/updateStuffPage";
    }

    @PostMapping("{id}/edit")
    public String updateCustomerAdminPage(@PathVariable(value = "id") long id, @RequestParam String newCustomerName,
                                          String newCustomerEmail, String newCustomerPassword,
                                          String newCustomerNumber, Posts newStuffPost, Model model) {
        Stuff stuff = stuffRepository.findById(id).orElseThrow();
        stuff.setName(newCustomerName);
        stuff.setEmail(newCustomerEmail);
        stuff.setPassword(newCustomerPassword);
        stuff.setNumber(newCustomerNumber);
        stuff.setPost_name(newStuffPost);
        stuffRepository.save(stuff);
        return "redirect:/stuff";
    }

    @PostMapping("{id}/remove")
    public String removeCustomerAdminPage(@PathVariable(value = "id") long id, Model model) {
        Stuff stuff = stuffRepository.findById(id).orElseThrow();
        stuffRepository.delete(stuff);
        return "redirect:/stuff";
    }
}

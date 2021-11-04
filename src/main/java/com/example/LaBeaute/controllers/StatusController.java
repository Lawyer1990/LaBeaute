package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Statuses;
import com.example.LaBeaute.repo.StatusesRepository;
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
public class StatusController {
    @Autowired
    private StatusesRepository statusesRepository;

    @GetMapping("/status")
    public String showPosts(Model model) {
        Iterable<Statuses> status  = statusesRepository.findAll();
        model.addAttribute("status", status);
        model.addAttribute("titleStatus", "Информация о статусе");
        return "status/statusPage";
    }

    @PostMapping("/status/add")
    public String addPostPage(@RequestParam String newStatus, Model model) {
        Statuses status = new Statuses(newStatus);
        statusesRepository.save(status);
        return "redirect:/status";
    }

    @GetMapping("/status/{id}/edit")
    public String doAdminEditPostPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Редактирование статуса");
        Optional<Statuses> status = statusesRepository.findById(id);
        ArrayList<Statuses> arrayList = new ArrayList<>();
        status.ifPresent(arrayList::add);
        model.addAttribute("statusId", arrayList);
        return "status/updateStatusPage";
    }

    @PostMapping("/status/{id}/edit")
    public String updatePostAdminPage(@PathVariable(value = "id") long id, @RequestParam String newStatus, Model model) {
        Statuses status = statusesRepository.findById(id).orElseThrow();
        status.setStatus(newStatus);
        statusesRepository.save(status);
        return "redirect:/status";
    }

    @PostMapping("/status/{id}/remove")
    public String removePostAdminPage(@PathVariable(value = "id") long id, Model model) {
        Statuses status = statusesRepository.findById(id).orElseThrow();
        statusesRepository.delete(status);
        return "redirect:/status";
    }
}

package com.example.LaBeaute.controllers;

import com.example.LaBeaute.models.Services;
import com.example.LaBeaute.repo.ServicesRepository;
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
public class ServicesController {
    @Autowired
    private ServicesRepository servicesRepository;

    @GetMapping("/services")
    public String showPosts(Model model) {
        Iterable<Services> services = servicesRepository.findAll();
        model.addAttribute("services", services);
        model.addAttribute("titleServices", "Информация о услугах");
        return "service/servicesPage";
    }

    @PostMapping("/services/add")
    public String addPostPage(@RequestParam String newService, Model model) {
        Services services = new Services(newService);
        servicesRepository.save(services);
        return "redirect:/services";
    }

    @GetMapping("/services/{id}/edit")
    public String doAdminEditPostPage(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("title", "Update Post Page");
        Optional<Services> services = servicesRepository.findById(id);
        ArrayList<Services> arrayList = new ArrayList<>();
        services.ifPresent(arrayList::add);
        model.addAttribute("serviceId", arrayList);
        return "service/updateServicesPage";
    }

    @PostMapping("/services/{id}/edit")
    public String updatePostAdminPage(@PathVariable(value = "id") long id, @RequestParam String newService, Model model) {
        Services services = servicesRepository.findById(id).orElseThrow();
        services.setService(newService);
        servicesRepository.save(services);
        return "redirect:/services";
    }

    @PostMapping("/services/{id}/remove")
    public String removePostAdminPage(@PathVariable(value = "id") long id, Model model) {
        Services services = servicesRepository.findById(id).orElseThrow();
        servicesRepository.delete(services);
        return "redirect:/services";
    }
}

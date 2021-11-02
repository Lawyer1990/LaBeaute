package com.example.LaBeaute.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SingInController {

    @GetMapping("/singIn")
    public String doSingInPage(Model model) {
        model.addAttribute("title", "Вход в аккаунт");
        return "singInPage";
    }
}

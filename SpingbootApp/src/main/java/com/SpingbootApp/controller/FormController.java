package com.SpingbootApp.controller;

import com.SpingbootApp.entity.DemoUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class FormController {

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("user", new DemoUser());
        return "form"; // Loads form.html
    }

    @PostMapping("/submitForm")
    public String submitForm(@ModelAttribute DemoUser user, Model model) {
        model.addAttribute("user", user);
        return "success"; // Redirect to success.html
    }
}
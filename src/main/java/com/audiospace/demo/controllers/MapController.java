package com.audiospace.demo.controllers;

import com.audiospace.demo.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MapController {
    @GetMapping("/map")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "/map";
    }
}

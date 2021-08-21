package com.audiospace.demo.controllers;

import com.audiospace.demo.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/")

    public String hello() {
        return "home";

    }

    //added employees
    @GetMapping("/employees")
    public String employees() {
        return "employees";
    }

    //added contact
    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }
}

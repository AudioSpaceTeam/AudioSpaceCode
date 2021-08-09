package com.audiospace.demo.controllers;
import com.audiospace.demo.models.User;
import com.audiospace.demo.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    private final UserRepository userDao;
    public ProfileController(UserRepository userDao){
        this.userDao = userDao;
    }

    @GetMapping("/profile")
    public String showUserInfo(Model model){
        model.addAttribute("user", new User());
        return "/profile";
    }

}
